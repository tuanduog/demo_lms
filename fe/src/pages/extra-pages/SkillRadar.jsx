import React from 'react';
import { Radar, RadarChart, PolarGrid, PolarAngleAxis, PolarRadiusAxis, Tooltip, ResponsiveContainer } from 'recharts';
import { Card, CardContent, Typography } from '@mui/material';

const data = [
  { subject: 'Java', A: 100, fullMark: 100 },
  { subject: 'Spring', A: 80, fullMark: 100 },
  { subject: 'React', A: 90, fullMark: 100 },
  { subject: 'Database', A: 99, fullMark: 100 },
  { subject: 'DevOps', A: 20, fullMark: 100 },
  { subject: 'AI/ML', A: 0, fullMark: 100 }
];

const SkillRadar = () => {
  return (
    <Card sx={{ width: 600, mx: 'auto', mt: 4, boxShadow: 5 }}>
      <CardContent>
        <Typography variant="h5" fontWeight="bold" textAlign="center" gutterBottom>
          🕸️ Kỹ năng tổng quan
        </Typography>
        <ResponsiveContainer width="100%" height={400}>
          <RadarChart cx="50%" cy="50%" outerRadius="80%" data={data}>
            <PolarGrid />
            <PolarAngleAxis dataKey="subject" />
            <PolarRadiusAxis angle={180} />
            <Tooltip />
            <Radar name="Năng lực" dataKey="A" stroke="#1976d2" fill="#1976d2" fillOpacity={0.6} />
          </RadarChart>
        </ResponsiveContainer>
      </CardContent>
    </Card>
  );
};

export default SkillRadar;
