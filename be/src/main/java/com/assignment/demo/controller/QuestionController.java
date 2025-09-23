package com.assignment.demo.controller;

import com.assignment.demo.model.*;
import com.assignment.demo.repository.*;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @PostMapping("/add-question")
    public ResponseEntity<?> addQuestion(@RequestParam("file") MultipartFile file) throws IOException {
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

        Integer materialIndex = columnIndexMap.get("Material");
        Integer urlIndex = columnIndexMap.get("Main_content");
        Integer numberOfQuestionIndex = columnIndexMap.get("Number_of_question");
        Integer questionTypeIndex = columnIndexMap.get("Question_type");

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) continue;

            // check section, type, question_type
            // Cell sectionCell = row.getCell(sectionIndex);
            // String sectionValue = sectionCell.getStringCellValue();
            // if(sectionValue != null && !sectionValue.isEmpty()){
            //continue;
            // }

            String materialValue = cellHelper.getCellValue(row.getCell(materialIndex));
            QuestionMaterial questionMaterial = null;
            if (materialValue != null && !materialValue.isEmpty()) {
                questionMaterial = new QuestionMaterial();
                questionMaterial.setType(materialValue);
                questionMaterial.setUrl(cellHelper.getCellValue(row.getCell(urlIndex)));
                questionMaterial.setQuantity(safeParseInt(cellHelper.getCellValue(row.getCell(numberOfQuestionIndex)), 0));

                questionMaterialRepository.save(questionMaterial);
            }

            int count = safeParseInt(cellHelper.getCellValue(row.getCell(numberOfQuestionIndex)), 1);
            int j = i + count - 1;
            String questionTypeValue = cellHelper.getCellValue(row.getCell(questionTypeIndex));

            if (questionTypeValue != null && !questionTypeValue.isEmpty()) {
                if ("MCQ".equalsIgnoreCase(questionTypeValue) || "Ordering".equalsIgnoreCase(questionTypeValue)) {
                    for (int t = i; t <= j; t++) {
                        Row questionRow = sheet.getRow(t);
                        if (questionRow == null) continue;

                        Question q = new Question();
                        if (questionMaterial != null) {
                            q.setQuestionMaterial(questionMaterial);
                        }
                        q.setType(questionTypeValue);
                        q.setExplaination(cellHelper.getCellValue(questionRow.getCell(columnIndexMap.get("Explain"))));
                        q.setScore(safeParseDouble(cellHelper.getCellValue(questionRow.getCell(columnIndexMap.get("Score"))), 0.0));
                        q.setQuestionContent(cellHelper.getCellValue(questionRow.getCell(columnIndexMap.get("Question_content"))));
                        questionRepository.save(q);

                        String[] options = {
                                cellHelper.getCellValue(questionRow.getCell(columnIndexMap.get("Option_A"))),
                                cellHelper.getCellValue(questionRow.getCell(columnIndexMap.get("Option_B"))),
                                cellHelper.getCellValue(questionRow.getCell(columnIndexMap.get("Option_C"))),
                                cellHelper.getCellValue(questionRow.getCell(columnIndexMap.get("Option_D")))
                        };

                        String correct = cellHelper.getCellValue(questionRow.getCell(columnIndexMap.get("Correct_answer")));

                        for (String opt : options) {
                            if (opt == null || opt.isEmpty()) continue;
                            MCQAnswer ans = new MCQAnswer();
                            ans.setQuestion(q);
                            ans.setAnswerContent(opt);
                            ans.setCorrect(opt.equalsIgnoreCase(correct));
                            mcqAnswerRepository.save(ans);
                        } // chua can thiet -> de fix sau
                    }
                    i = j;
                }

                else if ("FillBlank".equalsIgnoreCase(questionTypeValue)) {
                    Question q = new Question();
                    if (questionMaterial != null) {
                        q.setQuestionMaterial(questionMaterial);
                    }
                    q.setType(questionTypeValue);
                    q.setExplaination(cellHelper.getCellValue(row.getCell(columnIndexMap.get("Explain"))));
                    q.setScore(safeParseDouble(cellHelper.getCellValue(row.getCell(columnIndexMap.get("Score"))), 0.0));
                    q.setQuestionContent(cellHelper.getCellValue(row.getCell(columnIndexMap.get("Question_content"))));
                    questionRepository.save(q);

                    String correct = cellHelper.getCellValue(row.getCell(columnIndexMap.get("Correct_answer")));
                    if (correct != null && !correct.isEmpty()) {
                        String[] ansList = correct.split(",");
                        int slotIndex = 0;
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

                else if ("Essay".equalsIgnoreCase(questionTypeValue)) {
                    Question q = new Question();
                    if (questionMaterial != null) {
                        q.setQuestionMaterial(questionMaterial);
                    }
                    q.setType(questionTypeValue);
                    q.setExplaination(cellHelper.getCellValue(row.getCell(columnIndexMap.get("Explain"))));
                    q.setScore(safeParseDouble(cellHelper.getCellValue(row.getCell(columnIndexMap.get("Score"))), 0.0));
                    q.setQuestionContent(cellHelper.getCellValue(row.getCell(columnIndexMap.get("Question_content"))));
                    questionRepository.save(q);
                }
            }
        }
        return ResponseEntity.ok().build();
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

    @GetMapping("/get-all-question")
    public ResponseEntity<?> getAllQuestion() {
        return ResponseEntity.ok(questionService.getQuestionStruct());
    }
}
