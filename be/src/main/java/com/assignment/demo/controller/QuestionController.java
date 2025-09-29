package com.assignment.demo.controller;

import com.assignment.demo.model.*;
import com.assignment.demo.repository.*;
import com.assignment.demo.service.AssignmentService;
import com.assignment.demo.service.QuestionService;
import com.assignment.demo.utils.CellHelper;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/api/question")
@CrossOrigin(origins = "http://localhost:5173")
public class QuestionController {

    @Autowired
    private QuestionMaterialRepository questionMaterialRepository;

    @Autowired
    private CellHelper cellHelper;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private MCQAnswerRepository mcqAnswerRepository;

    @Autowired
    private BlankQuestionRepository blankQuestionRepository;

    @Autowired
    private BlankAnswerRepository blankAnswerRepository;

    @Autowired
    private QuestionService  questionService;

    @Autowired
    private AssignmentService assignmentService;

    @Autowired
    private AssignmentSectionRepository  assignmentSectionRepository;

    @PostMapping("/add-question/{id}")
    public ResponseEntity<?> addQuestion(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
        XSSFSheet sheet = workbook.getSheetAt(0);

        Row headerRow = sheet.getRow(0);
        if (headerRow == null) {
            return ResponseEntity.badRequest().build();
        }

        Map<String, Integer> columnIndexMap = new HashMap<>();
        for (Cell cell : headerRow) {
            columnIndexMap.put(cell.getStringCellValue().trim(), cell.getColumnIndex());
        }

        Integer sectionIndex = columnIndexMap.get("Section");
        Integer materialIndex = columnIndexMap.get("Material");
        Integer urlIndex = columnIndexMap.get("Main_content");
        Integer numberOfQuestionIndex = columnIndexMap.get("Number_of_question");
        Integer questionTypeIndex = columnIndexMap.get("Question_type");
        Integer numberOfPartIndex = columnIndexMap.get("Number_of_part");

        int rowIndex = 1;
        while (rowIndex <= sheet.getLastRowNum()) {
            Row row = sheet.getRow(rowIndex);
            if (row == null) {
                rowIndex++;
                continue;
            }

            // ====== Tạo Section ======
            String sectionValue = cellHelper.getCellValue(row.getCell(sectionIndex));
            if (sectionValue == null || sectionValue.isEmpty()) {
                rowIndex++;
                continue;
            }

            int numberOfPart = safeParseInt(cellHelper.getCellValue(row.getCell(numberOfPartIndex)), 1);
            AssignmentSection assignmentSection = new AssignmentSection();
            assignmentSection.setName(sectionValue);
            assignmentSection.setAssignment(assignmentService.getOneToTest(id)); // fake assignment test
            assignmentSectionRepository.save(assignmentSection);

            // ====== Xử lý Materials trong section ======
            int sectionEnd = rowIndex + numberOfPart - 1;
            for (int v = rowIndex; v <= sectionEnd; v++) { // 7-10
                Row materialRow = sheet.getRow(v);
                if (materialRow == null) continue;

                String materialValue = cellHelper.getCellValue(materialRow.getCell(materialIndex));
                QuestionMaterial questionMaterial = new QuestionMaterial();
                questionMaterial.setType(materialValue);
                questionMaterial.setUrl(cellHelper.getCellValue(materialRow.getCell(urlIndex)));
                questionMaterial.setQuantity(safeParseInt(cellHelper.getCellValue(materialRow.getCell(numberOfQuestionIndex)), 0));
                questionMaterial.setAssignmentSection(assignmentSection);
                questionMaterialRepository.save(questionMaterial);

                // ====== Xử lý Questions trong material ======
                int count = safeParseInt(cellHelper.getCellValue(materialRow.getCell(numberOfQuestionIndex)), 1);
                int materialEnd = v + count - 1;
                sectionEnd += count - 1;
                String questionTypeValue = cellHelper.getCellValue(materialRow.getCell(questionTypeIndex));
                for (int t = v; t <= materialEnd; t++) {
                    Row questionRow = sheet.getRow(t);
                    if (questionRow == null) continue;

//                    String questionTypeValue = cellHelper.getCellValue(questionRow.getCell(questionTypeIndex));
//                    if (questionTypeValue == null || questionTypeValue.isEmpty()) continue;

                    Question q = new Question();
                    q.setQuestionMaterial(questionMaterial);
                    q.setType(questionTypeValue);
                    q.setExplaination(cellHelper.getCellValue(questionRow.getCell(columnIndexMap.get("Explain"))));
                    q.setScore(safeParseDouble(cellHelper.getCellValue(questionRow.getCell(columnIndexMap.get("Score"))), 0.0));
                    q.setQuestionContent(cellHelper.getCellValue(questionRow.getCell(columnIndexMap.get("Question_content"))));
                    questionRepository.save(q);

                    // Xử lý theo loại câu hỏi
                    if ("MCQ".equalsIgnoreCase(questionTypeValue)) {
                        String[] options = {
                                cellHelper.getCellValue(questionRow.getCell(columnIndexMap.get("Option_A"))),
                                cellHelper.getCellValue(questionRow.getCell(columnIndexMap.get("Option_B"))),
                                cellHelper.getCellValue(questionRow.getCell(columnIndexMap.get("Option_C"))),
                                cellHelper.getCellValue(questionRow.getCell(columnIndexMap.get("Option_D")))
                        };
                        String correct = cellHelper.getCellValue(questionRow.getCell(columnIndexMap.get("Correct_answer")));
                        List<String> ansList = new ArrayList<>(Arrays.asList(correct.split(",")));
                        for (String opt : options) {
                            if (opt == null || opt.isEmpty()) continue;
                            MCQAnswer ans = new MCQAnswer();
                            ans.setQuestion(q);
                            ans.setAnswerContent(opt);
                            Iterator<String> it = ansList.iterator();
                            while (it.hasNext()) {
                                String a = it.next();
                                if (a.trim().equalsIgnoreCase(opt)) {
                                    ans.setCorrect(true);
                                    it.remove();
                                    break;
                                }
                            }
                            mcqAnswerRepository.save(ans);
                        }
                    }
                    else if ("FillBlank".equalsIgnoreCase(questionTypeValue)) {
                        String correct = cellHelper.getCellValue(questionRow.getCell(columnIndexMap.get("Correct_answer")));
                        if (correct != null && !correct.isEmpty()) {
                            String[] ansList = correct.split(",");
                            long slotIndex = 1;
                            for (String a : ansList) {
                                BlankQuestion blankQ = new BlankQuestion();
                                blankQ.setQuestion(q);
                                blankQ.setBlankSlot(slotIndex);
                                blankQuestionRepository.save(blankQ);

                                BlankAnswer blankAns = new BlankAnswer();
                                blankAns.setBlankQuestion(blankQ);
                                blankAns.setAnswerContent(a.trim());
                                blankAnswerRepository.save(blankAns);
                                slotIndex++;
                            }
                        }
                    }
                    // Essay thì chỉ lưu question
                }
                v = materialEnd;
            }

            rowIndex = sectionEnd + 1; // sang section mới
        }

        return ResponseEntity.ok("Import success");
    }


    private int safeParseInt(String value, int defaultVal) {
        try {
            if (value != null && !value.trim().isEmpty()) {
                return Integer.parseInt(value.trim());
            }
        } catch (NumberFormatException ignored) {}
        return defaultVal;
    }

    private double safeParseDouble(String value, double defaultVal) {
        try {
            if (value != null && !value.trim().isEmpty()) {
                return Double.parseDouble(value.trim());
            }
        } catch (NumberFormatException ignored) {}
        return defaultVal;
    }

    @GetMapping("/get-all-question/{asignId}")
    public ResponseEntity<?> getAllQuestion(@PathVariable Long asignId) {
        return ResponseEntity.ok(questionService.getQuestionStruct(asignId));
    }

    @GetMapping("/check-type/{asignId}")
    public ResponseEntity<?> checkType(@PathVariable Long asignId) {
        boolean check = questionService.checkType(asignId);
        return ResponseEntity.ok(check);
    }
}
