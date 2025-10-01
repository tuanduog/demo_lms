import React, { useState, useEffect, useMemo } from 'react';
import { useParams } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import { Box, Typography, Card, CardContent, TextField, Chip, Divider, LinearProgress, Button, Paper, Tabs, Tab } from '@mui/material';
import axios from 'axios';
import { set } from 'lodash-es';
export default function AssignmentDetail() {
  const [report, setReport] = useState([]);
  const { id } = useParams();
  const [answers, setAnswers] = useState([]);
  const [review, setReview] = useState('');
  const [tabValue, setTabValue] = useState(0);

  // 📊 Tổng điểm toàn bài
  const totalScore = useMemo(() => answers.reduce((sum, a) => sum + (Number(a.score) || 0), 0), [answers]);
  const navigate = useNavigate();
  const loadData = async () => {
    try {
      const response = await axios.get(`http://localhost:8080/grading/${id}`);
      setReport(response.data);
      setAnswers(response.data.answerList || []);
      setReview(response.data.review || '');
    } catch (err) {
      console.log(err);
    }
  };
  useEffect(() => {
    loadData();
  }, []);

  // 📂 Gom nhóm câu hỏi theo section
  const groupedSections = useMemo(() => {
    const groups = {};
    answers.forEach((ans) => {
      if (!groups[ans.assignmentSectionId]) groups[ans.assignmentSectionId] = [];
      groups[ans.assignmentSectionId].push(ans);
    });
    return groups;
  }, [answers]);

  const sectionIds = Object.keys(groupedSections).sort((a, b) => a - b);

  // ✏️ Nhập điểm cho câu tự luận
  const handleScoreChange = (id, value) => {
    setAnswers((prev) => prev.map((a) => (a.questionSubmissionId === id ? { ...a, score: Number(value) || 0 } : a)));
  };

  // 📤 Gửi kết quả về backend
  const handleSubmitGrades = async () => {
    console.log(
      'Submitting grades:',
      answers.map((a) => ({ questionSubmissionId: a.questionSubmissionId, score: Number(a.score) || 0 }))
    );
    try {
      const response = await axios.post(
        `http://localhost:8080/grading/${id}`,
        {
          scoreList: answers.map((a) => ({ questionSubmissionId: a.questionSubmissionId, score: Number(a.score) || 0 })),
          review: review || ''
        },
        { headers: { 'Content-Type': 'application/json' } }
      );

      console.log('Grading response:', response.data);
    } catch (err) {
      console.log(err);
    }
    navigate('/sample-page');
  };

  return (
    <Box p={4} bgcolor="#f5f6fa" minHeight="100vh">
      {/* 🧾 Thông tin bài */}
      <Typography variant="h4" gutterBottom>
        📊 Chấm điểm: {report.title}
      </Typography>
      <Typography variant="subtitle1" gutterBottom>
        👤 SV ID: {report.studentID} | 📘 Lớp: {report.className}
      </Typography>

      {/* 📊 Thanh điểm tổng */}
      <Box width="60%" mb={4}>
        <LinearProgress variant="determinate" value={(totalScore / 100) * 100} sx={{ height: 14, borderRadius: 6 }} />
        <Typography variant="body1" mt={1}>
          Tổng điểm hiện tại: <b>{totalScore.toFixed(2)}</b> / 100
        </Typography>
      </Box>

      <Divider sx={{ my: 3 }} />

      {/* 🧠 Tabs các section */}
      {sectionIds.length > 0 && (
        <>
          <Tabs value={tabValue} onChange={(e, newValue) => setTabValue(newValue)} variant="scrollable" scrollButtons="auto" sx={{ mb: 3 }}>
            {sectionIds.map((sid, index) => (
              <Tab key={sid} label={`📚 Phần ${index + 1}`} />
            ))}
          </Tabs>

          {/* Nội dung từng tab */}
          {sectionIds.map((sid, index) => {
            if (tabValue !== index) return null;
            const sectionQuestions = groupedSections[sid];
            const sectionScore = sectionQuestions.reduce((s, q) => s + (Number(q.score) || 0), 0);

            return (
              <Box key={sid}>
                <Typography variant="h5" gutterBottom>
                  📚 Phần {index + 1} – Tổng điểm: <b>{sectionScore.toFixed(2)}</b>
                </Typography>

                {sectionQuestions.map((ans) => (
                  <Card key={ans.questionSubmissionId} sx={{ mb: 2, boxShadow: 2 }}>
                    <CardContent>
                      <Typography variant="h6">
                        Câu {ans.questionId}: {ans.questionContent}
                      </Typography>

                      <Typography variant="body2" color="text.secondary" mt={1}>
                        📝 Trả lời: {ans.studentAnswer}
                      </Typography>

                      {ans.isCorrect !== null ? (
                        <Box mt={2} display="flex" alignItems="center" gap={2}>
                          <Chip label={ans.isCorrect ? '✅ Đúng' : '❌ Sai'} color={ans.isCorrect ? 'success' : 'error'} />
                          <Typography>
                            Điểm: <b>{ans.score}</b>
                          </Typography>
                        </Box>
                      ) : (
                        <Box mt={2}>
                          <TextField
                            label="Nhập điểm"
                            type="number"
                            variant="outlined"
                            size="small"
                            value={ans.score}
                            onChange={(e) => handleScoreChange(ans.questionSubmissionId, e.target.value)}
                            sx={{ width: 200 }}
                          />
                        </Box>
                      )}
                    </CardContent>
                  </Card>
                ))}
              </Box>
            );
          })}
        </>
      )}

      <Paper elevation={2} sx={{ p: 3, mt: 5, border: '4px solid lightgreen' }}>
        <Typography variant="h6" gutterBottom>
          ✍️ Nhận xét bài làm
        </Typography>
        <TextField
          fullWidth
          multiline
          minRows={3}
          placeholder="Nhập nhận xét..."
          value={review}
          onChange={(e) => setReview(e.target.value)}
        />
        <Box mt={4}>
          <Button variant="contained" size="large" onClick={() => handleSubmitGrades()}>
            ✅ Hoàn tất chấm điểm
          </Button>
        </Box>
      </Paper>
    </Box>
  );
}
