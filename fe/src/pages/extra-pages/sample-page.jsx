// material-ui
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import Button from '@mui/material/Button';
import axios from 'axios';
import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

// ==============================|| SAMPLE PAGE ||============================== //

export default function SamplePage() {
  const [data, setData] = useState([]);
  const navigate = useNavigate();
  const loadData = async () => {
    try {
      const response = await axios.get('http://localhost:8080/grading');
      console.log(response.data);
      setData(response.data);
    } catch (err) {
      console.log(err);
    }
  };
  useEffect(() => {
    loadData();
  }, []);

  return (
    <Box sx={{ minWidth: 300, mb: 2 }}>
      {data.map((item, index) => (
        <Card variant="outlined" key={index}>
          <CardContent>
            <Box sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'flex-start' }}>
              <Box>
                <Typography variant="h6" component="div" sx={{ fontWeight: 'bold', mb: 1 }}>
                  {item.title}
                </Typography>
                <Typography sx={{ color: 'text.secondary', fontSize: 14, mb: 0.5 }}>{item.assignmentType}</Typography>
                <Typography variant="body2" sx={{ fontStyle: 'italic', mb: 1 }}>
                  {item.review}
                </Typography>
              </Box>
              <Box sx={{ textAlign: 'right' }}>
                <Typography sx={{ color: 'orange', fontSize: 14, mb: 1 }}>{item.gradingStatus}</Typography>
                <Typography variant="body1" sx={{ mb: 1 }}>
                  Điểm: <b>{item.score}</b>
                </Typography>
                <Typography sx={{ color: 'text.secondary', fontSize: 12 }}>{item.modifedAt}</Typography>
              </Box>
            </Box>
          </CardContent>

          <CardActions>
            <Button size="small" variant="outlined" onClick={() => navigate(`/sample-page/${item.scoreReportID}`)}>
              Xem chi tiết
            </Button>
          </CardActions>
        </Card>
      ))}
    </Box>
  );
}
