import React, { useState, useEffect, useMemo } from 'react';
import { Card, CardContent, CardActions, Button, Typography, Grid, Autocomplete, TextField, Stack, Chip, Box } from '@mui/material';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
// const submissions = [
//   {
//     scoreReportID: 1,
//     studentID: 1,
//     className: 'CLS101',
//     title: 'Java Basics',
//     assignmentType: 'theory',
//     score: 105.5,
//     review: 'Good OOP',
//     modifiedAt: '2025-09-24T16:34:07',
//     gradingStatus: 'Completed'
//   },
//   {
//     scoreReportID: 2,
//     studentID: 2,
//     className: 'CLS102',
//     title: 'Math Quiz',
//     assignmentType: 'quiz',
//     score: 7.0,
//     review: 'Calc ok',
//     modifiedAt: '2025-09-24T16:34:07',
//     gradingStatus: 'pending'
//   }
//   // ... thêm toàn bộ JSON
// ];

const AssignmentList = () => {
  const navigate = useNavigate();
  const [selectedClasses, setSelectedClasses] = useState([]);
  const [selectedStatuses, setSelectedStatuses] = useState([]);
  const [submissions, setSubmissions] = useState([]);
  const classOptions = useMemo(() => [...new Set(submissions.map((d) => d.className))], [submissions]);
  const statusOptions = useMemo(() => [...new Set(submissions.map((d) => d.gradingStatus))], [submissions]);

  const loadData = async () => {
    try {
      const response = await axios.get('http://localhost:8080/grading');
      setSubmissions(response.data);
    } catch (err) {
      console.log(err);
    }
  };
  useEffect(() => {
    loadData();
  }, []);
  const filteredData = useMemo(() => {
    return submissions.filter(
      (d) =>
        (selectedClasses.length === 0 || selectedClasses.includes(d.className)) &&
        (selectedStatuses.length === 0 || selectedStatuses.includes(d.gradingStatus))
    );
  }, [selectedClasses, selectedStatuses, submissions]);

  const handleViewDetail = (submission) => {
    alert(`Xem chi tiết bài làm của ${submission.title}`);
  };

  const getStatusColor = (status) => {
    return status.toLowerCase() === 'completed' ? 'success' : 'warning';
  };

  return (
    <div style={{ padding: 24 }}>
      <Typography variant="h4" gutterBottom>
        Bài tập nộp của học viên
      </Typography>

      <Stack direction={{ xs: 'column', sm: 'row' }} spacing={2} marginBottom={3}>
        <Autocomplete
          multiple
          options={classOptions}
          getOptionLabel={(option) => option}
          value={selectedClasses}
          onChange={(e, newValue) => setSelectedClasses(newValue)}
          renderInput={(params) => <TextField {...params} label="Lọc theo lớp" />}
          style={{ width: 250 }}
        />

        <Autocomplete
          multiple
          options={statusOptions}
          getOptionLabel={(option) => option}
          value={selectedStatuses}
          onChange={(e, newValue) => setSelectedStatuses(newValue)}
          renderInput={(params) => <TextField {...params} label="Lọc theo trạng thái" />}
          style={{ width: 250 }}
        />
      </Stack>

      <Box sx={{ display: 'flex', flexDirection: 'column', gap: 2 }}>
        {filteredData.length > 0 ? (
          filteredData.map((sub) => (
            <Card
              key={sub.scoreReportID}
              variant="outlined"
              sx={{
                borderRadius: 3,
                width: '100%',
                display: 'flex',
                flexDirection: { xs: 'column', md: 'row' },
                padding: 2,
                boxShadow: 2,
                alignItems: 'stretch',
                '&:hover': { boxShadow: 6 }
              }}
            >
              {/* Left: Main Info */}
              <Box sx={{ flex: 2, pr: { md: 3 }, mb: { xs: 2, md: 0 } }}>
                <Typography variant="subtitle1" color="text.secondary" gutterBottom>
                  {sub.className}
                </Typography>
                <Typography variant="h3" fontWeight="bold" gutterBottom>
                  {sub.title}
                </Typography>
                <Typography variant="body1" sx={{ mb: 1 }}>
                  <strong>Review:</strong> {sub.review}
                </Typography>
                <Typography variant="body2" color="text.secondary">
                  <strong>Modified:</strong> {new Date(sub.modifiedAt).toLocaleString()}
                </Typography>
                <Typography variant="body2" color="text.secondary" sx={{ mt: 1 }}>
                  <strong>Type:</strong> {sub.assignmentType}
                </Typography>

                {/* Button xem chi tiết */}
                <Button size="medium" variant="contained" sx={{ mt: 2 }} onClick={() => navigate(`/sample-page/${sub.scoreReportID}`)}>
                  Xem chi tiết
                </Button>
              </Box>

              {/* Right: Score + Status */}
              <Box
                sx={{
                  flex: 1,
                  display: 'flex',
                  flexDirection: 'column',
                  alignItems: { xs: 'flex-start', md: 'flex-end' },
                  justifyContent: 'flex-start',
                  gap: 2
                }}
              >
                <Chip
                  label={` ${sub.score}`}
                  color="primary"
                  sx={{ fontSize: '150%', fontWeight: 'bold', p: 1, height: '50%', width: '70%' }}
                />
                <Chip
                  label={sub.gradingStatus}
                  color={getStatusColor(sub.gradingStatus)}
                  sx={{ fontSize: '150%', fontWeight: 'bold', p: 1, height: '50%', width: '70%' }}
                />
              </Box>
            </Card>
          ))
        ) : (
          <Typography variant="body1">Không có dữ liệu</Typography>
        )}
      </Box>
    </div>
  );
};

export default AssignmentList;
