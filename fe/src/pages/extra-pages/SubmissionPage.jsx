import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';
import {
  Container,
  Typography,
  Box,
  Tabs,
  Tab,
  Card,
  CardContent,
  TextField,
  Button,
  RadioGroup,
  FormControlLabel,
  Radio,
  Divider,
  Chip,
  Link,
  Alert
} from '@mui/material';
import AccessTimeIcon from '@mui/icons-material/AccessTime';
import PictureAsPdfIcon from '@mui/icons-material/PictureAsPdf';
import ImageIcon from '@mui/icons-material/Image';

const SubmissionPage = () => {
  const { id } = useParams();
  const [assignment, setAssignment] = useState(null);
  const [tabValue, setTabValue] = useState(0);
  const [timeLeft, setTimeLeft] = useState(null);
  const [answers, setAnswers] = useState({});
  const loadData = async () => {
    try {
      const response = await axios.get(`http://localhost:8080/test/${id}`);
      const data = response.data;
      setAssignment(data);
      setTimeLeft(data.duration * 120);
    } catch (err) {
      console.log(err);
    }
  };
  useEffect(() => {
    loadData();
    // 🧪 Giả lập fetch dữ liệu từ API
    // const data = {
    //   assignmentId: 1,
    //   title: 'Java Basics',
    //   description: 'Assignment on OOP concepts',
    //   repeatLimit: 1,
    //   assignmentType: 'theory',
    //   duration: 60,
    //   startTime: '2025-09-24T16:34:07',
    //   endTime: '2025-10-01T16:34:07',
    //   questionList: [
    //     {
    //       questionId: 1,
    //       assignmentSectionId: 1,
    //       questionType: 'mcq',
    //       materialURL: 'http://example.com/mat1.pdf',
    //       questionContent: 'What is OOP?',
    //       answerList: ['Object Oriented Programming', 'Other Programming Paradigm']
    //     },
    //     {
    //       questionId: 2,
    //       assignmentSectionId: 1,
    //       questionType: 'theory',
    //       materialURL: 'http://example.com/mat1.pdf',
    //       questionContent: 'Define derivative'
    //     },
    //     {
    //       questionId: 3,
    //       assignmentSectionId: 1,
    //       questionType: 'essay',
    //       materialURL: 'http://example.com/mat2.pdf',
    //       questionContent: 'Write essay about Newton'
    //     },
    //     {
    //       questionId: 4,
    //       assignmentSectionId: 1,
    //       questionType: 'lab',
    //       materialURL: 'http://example.com/img2.png',
    //       questionContent: 'Lab procedure for acid test'
    //     },
    //     {
    //       questionId: 5,
    //       assignmentSectionId: 2,
    //       questionType: 'mcq',
    //       materialURL: 'http://example.com/mat3.pdf',
    //       questionContent: 'What is cell theory?',
    //       answerList: ['All living things are made of cells', 'Cells are random']
    //     }
    //   ]
    // };
  }, []);

  useEffect(() => {
    if (!timeLeft) return;
    const timer = setInterval(() => {
      setTimeLeft((prev) => {
        if (prev <= 1) {
          clearInterval(timer);
          alert('Hết giờ làm bài!');
          return 0;
        }
        return prev - 1;
      });
    }, 1000);
    return () => clearInterval(timer);
  }, [timeLeft]);

  const formatTime = (seconds) => {
    const m = Math.floor(seconds / 60);
    const s = seconds % 60;
    return `${m} phút ${s < 10 ? '0' + s : s} giây`;
  };

  const handleAnswerChange = (questionId, field, value) => {
    setAnswers((prev) => ({
      ...prev,
      [questionId]: {
        ...prev[questionId],
        [field]: value
      }
    }));
  };

  const handleSubmit = async () => {
    const formatted = assignment.questionList.map((q) => {
      const ans = answers[q.questionId] || {};
      if (!ans.choice && !ans.text && !ans.file) {
        alert(` Vui lòng hoàn thành tất cả câu hỏi để nộp bài.`);
        return;
      }
      return {
        questionID: q.questionId,
        sectionID: q.assignmentSectionId || null,
        studentAnswer: ans.choice || ans.text || null,
        file: ans.file || null
      };
    });

    console.log('📤 Nộp bài với đáp án:', formatted);

    try {
      const response = await axios.post(`http://localhost:8080/test/${id}`, formatted, {
        headers: { 'Content-Type': 'application/json' }
      });
      alert(response.data || 'Thành công');
    } catch (err) {
      console.log(err);
    }
  };

  if (!assignment) return <Typography>Đang tải...</Typography>;

  const sections = Array.from(new Set(assignment.questionList.map((q) => q.assignmentSectionId)));

  return (
    <Container maxWidth="md" sx={{ mt: 4, mb: 10 }}>
      <Typography variant="h4" fontWeight="bold" gutterBottom>
        📝 {assignment.title}
      </Typography>
      <Typography variant="body1" mb={2}>
        {assignment.description}
      </Typography>

      <Box
        sx={{
          position: 'sticky',
          top: 74,
          zIndex: 1000,
          backgroundColor: 'white',
          py: 2,
          mb: 3,
          paddingX: 3,
          borderBottom: '2px solid #eee',
          borderRadius: 3,
          display: 'flex',
          alignItems: 'center',
          justifyContent: 'space-between'
        }}
      >
        <Box display="flex" alignItems="center" gap={2}>
          <Chip icon={<AccessTimeIcon />} label={`Thời lượng: ${assignment.duration} phút`} color="primary" />
          <Chip label={`Loại: ${assignment.assignmentType.toUpperCase()}`} color="secondary" />
        </Box>

        {timeLeft !== null && (
          <Alert
            severity={timeLeft > 60 * 10 ? 'info' : 'warning'}
            sx={{
              fontWeight: 'bold',
              m: 0,
              py: 0.5,
              backgroundColor: timeLeft > 60 * 10 ? 'lightgreen' : 'lightcoral',
              minWidth: '260px'
            }}
          >
            Còn lại: {formatTime(timeLeft)}
          </Alert>
        )}
      </Box>

      {/* Tabs chia theo section */}
      <Tabs value={tabValue} onChange={(e, newValue) => setTabValue(newValue)} variant="scrollable" scrollButtons="auto" sx={{ mb: 3 }}>
        {sections.map((sectionId, index) => (
          <Tab key={sectionId} label={`Phần ${index + 1}`} />
        ))}
      </Tabs>

      {sections.map((sectionId, index) => (
        <Box key={sectionId} hidden={tabValue !== index}>
          {/*  Gom nhóm câu hỏi theo materialURL */}
          {Object.entries(
            assignment.questionList
              .filter((q) => q.assignmentSectionId === sectionId)
              .reduce((acc, q) => {
                if (!acc[q.materialURL]) acc[q.materialURL] = [];
                acc[q.materialURL].push(q);
                return acc;
              }, {})
          ).map(([materialURL, questions], groupIdx) => (
            <Card key={groupIdx} sx={{ mb: 4, borderRadius: 3, boxShadow: 3 }}>
              <CardContent>
                {materialURL !== 'null' ? (
                  <Box mb={3}>
                    <Link href={materialURL} target="_blank" rel="noopener" sx={{ display: 'flex', alignItems: 'center', gap: 1 }}>
                      {materialURL.endsWith('.pdf') ? <PictureAsPdfIcon color="error" /> : <ImageIcon color="primary" />}
                      <Typography variant="h6"> Tài liệu tham khảo</Typography>
                    </Link>

                    {materialURL.endsWith('.pdf') ? (
                      <iframe
                        src={materialURL}
                        title={`material-${groupIdx}`}
                        width="100%"
                        height="400px"
                        style={{ border: '1px solid #ccc', borderRadius: '8px', marginTop: '10px' }}
                      ></iframe>
                    ) : (
                      <Box textAlign="center" mt={2}>
                        <img src={materialURL} alt="Material" style={{ maxWidth: '100%', borderRadius: '8px', border: '1px solid #ccc' }} />
                      </Box>
                    )}
                  </Box>
                ) : null}

                {/*  Danh sách câu hỏi thuộc cùng tài liệu */}
                {questions.map((q, idx) => (
                  <Box key={q.questionId} sx={{ mb: 4 }}>
                    <Typography variant="h6" mb={1}>
                      Câu {q.questionId}: {q.questionContent}
                    </Typography>
                    {/*  Input theo loại câu hỏi */}
                    {q.questionType === 'mcq' && (
                      <RadioGroup
                        value={answers[q.questionId]?.choice || ''}
                        onChange={(e) => handleAnswerChange(q.questionId, 'choice', e.target.value)}
                      >
                        {q.answerList?.map((ans, i) => (
                          <FormControlLabel key={i} value={ans.answerContent} control={<Radio />} label={ans.answerContent} />
                        ))}
                      </RadioGroup>
                    )}

                    {['theory', 'essay'].includes(q.questionType) && (
                      <Box display="flex" flexDirection="column" gap={2}>
                        <TextField
                          fullWidth
                          multiline
                          rows={4}
                          placeholder="Nhập câu trả lời..."
                          value={answers[q.questionId]?.text || ''}
                          onChange={(e) => handleAnswerChange(q.questionId, 'text', e.target.value)}
                        />

                        <Button variant="outlined" component="label">
                          📤 Tải file câu trả lời (tuỳ chọn)
                          <input hidden type="file" onChange={(e) => handleAnswerChange(q.questionId, 'file', e.target.files[0] || null)} />
                        </Button>

                        {/* {answers[q.questionId]?.file && (
                          <Typography variant="body2" color="text.secondary">
                            📎 Đã chọn: {answers[q.questionId].file.name}
                          </Typography>
                        )} */}
                      </Box>
                    )}

                    {q.questionType === 'lab' && (
                      <Button variant="outlined" component="label">
                        📤 Tải file kết quả
                        <input hidden type="file" onChange={(e) => handleAnswerChange(q.questionId, 'file', e.target.files[0] || null)} />
                      </Button>
                    )}

                    {q.questionType === 'quiz' && (
                      <TextField
                        fullWidth
                        placeholder="Nhập đáp án quiz..."
                        value={answers[q.questionId]?.choice || ''}
                        onChange={(e) => handleAnswerChange(q.questionId, 'choice', e.target.value)}
                      />
                    )}
                  </Box>
                ))}
              </CardContent>
            </Card>
          ))}
        </Box>
      ))}

      <Divider sx={{ my: 4 }} />

      <Box textAlign="center">
        <Button variant="contained" color="success" size="large" onClick={handleSubmit} disabled={timeLeft === 0}>
          📤 Nộp bài
        </Button>
      </Box>
    </Container>
  );
};

export default SubmissionPage;
