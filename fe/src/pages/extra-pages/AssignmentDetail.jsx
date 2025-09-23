import { useParams } from 'react-router-dom';
import { useState, useEffect } from 'react';
import axios from 'axios';
import { Card, CardContent, Typography, Grid, Divider, List, ListItem, ListItemText, Box, Chip, Tabs, Tab, TextField } from '@mui/material';
import QuestionItem from '../../components/question/QuestionItem';
const AssignmentDetail = () => {
  const [tabValue, setTabValue] = useState(0);
  const [assignment, setAssignment] = useState({});
  const [manualGrading, setManualGrading] = useState({});
  const { id } = useParams();
  const { scoreReportID, studentID, title, description, assignmentType, answerList, score, review, modifiedAt, gradingStatus } = assignment;

  const fetchAssignmentDetail = async () => {
    try {
      const response = await axios.get(`http://localhost:8080/grading/${id}`);
      setAssignment(response.data);
      if (response.data.answerList) {
        const initialScores = {};
        response.data.answerList.forEach((q) => {
          initialScores[q.questionSubmissionId] = q.score ?? '';
        });
        setManualGrading(initialScores);
      }
      console.log(response.data);
    } catch (err) {
      console.log(err);
    }
  };
  useEffect(() => {
    fetchAssignmentDetail();
  }, []);

  //   Section tab
  function TabPanel({ children, value, index }) {
    return (
      <div role="tabpanel" hidden={value !== index} id={`simple-tabpanel-${index}`} aria-labelledby={`simple-tab-${index}`}>
        {value === index && <Box sx={{ p: 2 }}>{children}</Box>}
      </div>
    );
  }

  const groupedAnswers =
    answerList?.reduce((acc, ans) => {
      if (!acc[ans.assignmentSectionId]) {
        acc[ans.assignmentSectionId] = [];
      }
      acc[ans.assignmentSectionId].push(ans);
      return acc;
    }, {}) || {};
  console.log('group:', groupedAnswers);
  const sectionIds = Object.keys(groupedAnswers);
  //   ----------------

  const handleChangeScore = (event, questionId) => {
    const newScore = event;
    setManualGrading((prev) => ({
      ...prev,
      [questionId]: newScore
    }));
  };
  return (
    <>
      <Box p={3}>
        {/* Thông tin Assignment */}
        <Card sx={{ mb: 3 }}>
          <CardContent>
            <Typography variant="h5" gutterBottom>
              {title}
            </Typography>
            <Typography variant="body1" color="text.secondary" gutterBottom>
              {description}
            </Typography>

            <Grid container spacing={2} mt={1}>
              <Grid item xs={6}>
                <Typography variant="subtitle2">Assignment Type:</Typography>
                <Typography>{assignmentType}</Typography>
              </Grid>
              <Grid item xs={6}>
                <Typography variant="subtitle2">Grading Status:</Typography>
                <Chip label={gradingStatus} color={gradingStatus === 'completed' ? 'success' : 'warning'} />
              </Grid>
              <Grid item xs={6}>
                <Typography variant="subtitle2">Score:</Typography>
                <Typography>{score ?? 'Chưa chấm'}</Typography>
              </Grid>
              <Grid item xs={6}>
                <Typography variant="subtitle2">Modified At:</Typography>
                <Typography>{new Date(modifiedAt).toLocaleString('vi-VN')}</Typography>
              </Grid>
            </Grid>
          </CardContent>
        </Card>

        {/* Review */}
        {review && (
          <Card sx={{ mb: 3 }}>
            <CardContent>
              <Typography variant="h6">Giáo viên nhận xét</Typography>
              <Typography variant="body1">{review}</Typography>
            </CardContent>
          </Card>
        )}

        {/* Tabs cho từng Section */}
        <Card>
          <CardContent>
            <Typography variant="h6" gutterBottom>
              Câu hỏi & Câu trả lời
            </Typography>
            <Divider sx={{ mb: 2 }} />

            {sectionIds.length > 0 ? (
              <>
                <Tabs value={tabValue} onChange={(e, newValue) => setTabValue(newValue)} variant="scrollable" scrollButtons="auto">
                  {sectionIds.map((sid, idx) => (
                    <Tab key={sid} label={`Section ${sid}`} />
                  ))}
                </Tabs>

                {sectionIds.map((sid, idx) => (
                  <TabPanel key={sid} value={tabValue} index={idx}>
                    <List>
                      {groupedAnswers[sid].map((q, index) => {
                        const scoreValue = manualGrading[q.questionSubmissionId] ?? '';
                        console.log('score:', scoreValue);
                        return (
                          <QuestionItem
                            question={q}
                            scoreValue={scoreValue}
                            onScoreChange={handleChangeScore}
                            gradingStatus={gradingStatus}
                            index={index}
                          />
                        );
                      })}
                    </List>
                  </TabPanel>
                ))}
              </>
            ) : (
              <Typography>Không có câu trả lời nào.</Typography>
            )}
          </CardContent>
        </Card>
      </Box>
    </>
  );
};
export default AssignmentDetail;
