import React, { useState, useEffect } from 'react';
import { Container, Typography, Grid, Card, CardContent, CardActions, Button, Chip, Box } from '@mui/material';
import AccessTimeIcon from '@mui/icons-material/AccessTime';
import AssignmentIcon from '@mui/icons-material/Assignment';
import ReplayIcon from '@mui/icons-material/Replay';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
const AssignmentsPage = () => {
  const [assignments, setAssignments] = useState([]);
  const navigate = useNavigate();
  // ⚡ Giả lập dữ liệu từ API (bạn có thể thay bằng fetch thực tế)
  const loadData = async () => {
    try {
      const response = await axios.get('http://localhost:8080/test');

      setAssignments(response.data);
    } catch (err) {
      console.log(err);
    }
  };
  useEffect(() => {
    loadData();
  }, []);

  const formatDate = (dateStr) =>
    new Date(dateStr).toLocaleString('vi-VN', {
      day: '2-digit',
      month: '2-digit',
      year: 'numeric',
      hour: '2-digit',
      minute: '2-digit'
    });

  const getStatus = (start, end) => {
    const now = new Date();
    if (now < new Date(start)) return 'Chưa bắt đầu';
    if (now > new Date(end)) return 'Đã kết thúc';
    return 'Đang mở';
  };

  const getStatusColor = (status) => {
    switch (status) {
      case 'Đang mở':
        return 'success';
      case 'Chưa bắt đầu':
        return 'warning';
      case 'Đã kết thúc':
        return 'error';
      default:
        return 'default';
    }
  };

  return (
    <Container maxWidth="md" sx={{ mt: 5 }}>
      <Typography variant="h4" fontWeight="bold" gutterBottom>
        📘 Danh sách bài tập
      </Typography>

      <Grid container spacing={3}>
        {assignments.map((item) => {
          const status = getStatus(item.startTime, item.endTime);
          return (
            <Grid item xs={12} key={item.id}>
              <Card
                sx={{
                  borderRadius: 3,
                  boxShadow: 3,
                  transition: '0.3s',
                  '&:hover': { boxShadow: 6, transform: 'translateY(-4px)' }
                }}
              >
                <CardContent>
                  <Box display="flex" justifyContent="space-between" mb={1}>
                    <Typography variant="h5" fontWeight="bold">
                      {item.title}
                    </Typography>
                    <Chip label={item.assignmentType.toUpperCase()} color="primary" size="small" icon={<AssignmentIcon />} />
                  </Box>

                  <Typography variant="body1" color="text.secondary" mb={2}>
                    {item.description}
                  </Typography>

                  <Box display="flex" alignItems="center" gap={2} flexWrap="wrap">
                    <Chip icon={<AccessTimeIcon />} label={`Bắt đầu: ${formatDate(item.startTime)}`} variant="outlined" />
                    <Chip icon={<AccessTimeIcon />} label={`Kết thúc: ${formatDate(item.endTime)}`} variant="outlined" />
                    <Chip label={`Thời lượng: ${item.duration} phút`} color="secondary" variant="outlined" />
                    <Chip icon={<ReplayIcon />} label={`Số lần làm lại: ${item.repeatLimit}`} variant="outlined" />
                    <Chip label={status} color={getStatusColor(status)} />
                  </Box>
                </CardContent>

                <CardActions sx={{ justifyContent: 'flex-end', pb: 2, pr: 2 }}>
                  <Button
                    variant="contained"
                    color="primary"
                    disabled={status !== 'Đang mở'}
                    onClick={() => navigate(`/submission/${item.id}`)}
                  >
                    Làm bài
                  </Button>
                </CardActions>
              </Card>
            </Grid>
          );
        })}
      </Grid>
    </Container>
  );
};

export default AssignmentsPage;
