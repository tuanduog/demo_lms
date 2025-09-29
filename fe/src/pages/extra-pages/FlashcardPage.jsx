import React, { useState, useEffect, useCallback } from 'react';
import { Card, CardContent, Typography, Button, Box, LinearProgress } from '@mui/material';
import { motion } from 'framer-motion';

const FlashcardPage = ({ data }) => {
  const [index, setIndex] = useState(0);
  const [flipped, setFlipped] = useState(false);

  const total = data.length;
  const current = data[index];

  const handleFlip = useCallback(() => {
    setFlipped((prev) => !prev);
  }, []);

  const handleNext = useCallback(() => {
    if (index + 1 >= total) return;

    setFlipped(false);
    setTimeout(() => {
      setIndex((prev) => prev + 1);
    }, 100);
  }, [index, total]);

  const handlePrev = useCallback(() => {
    if (index - 1 < 0) return;

    setFlipped(false);
    setTimeout(() => {
      setIndex((prev) => prev - 1);
    }, 100);
  }, [index]);

  useEffect(() => {
    const handleKeyDown = (e) => {
      if (e.code === 'Space' || e.code === 'ArrowUp' || e.code === 'ArrowDown') {
        e.preventDefault();
        handleFlip();
      } else if (e.code === 'ArrowRight') {
        handleNext();
      } else if (e.code === 'ArrowLeft') {
        handlePrev();
      }
    };

    window.addEventListener('keydown', handleKeyDown);
    return () => window.removeEventListener('keydown', handleKeyDown);
  }, [handleFlip, handleNext, handlePrev]);

  return (
    <Box display="flex" flexDirection="column" alignItems="center" justifyContent="center" bgcolor="#f5f6fa" px={2}>
      <Typography variant="h5" gutterBottom>
        📚 Flashcard Học Từ Vựng
      </Typography>

      <Box width="300px" mb={3}>
        <LinearProgress variant="determinate" value={((index + 1) / total) * 100} sx={{ height: 10, borderRadius: 5 }} />
        <Typography variant="body2" align="center" mt={1}>
          {index + 1} / {total}
        </Typography>
      </Box>

      <motion.div
        style={{
          width: '50%',
          height: 200,
          perspective: '1000px',
          cursor: 'pointer'
        }}
        onClick={handleFlip}
      >
        <motion.div
          animate={{ rotateY: flipped ? 180 : 360 }}
          transition={{ duration: 0.6 }}
          style={{
            position: 'relative',
            width: '100%',
            height: '100%',
            transformStyle: 'preserve-3d'
          }}
        >
          <Card
            sx={{
              position: 'absolute',
              width: '100%',
              height: '100%',
              backfaceVisibility: 'hidden',
              boxShadow: 4,
              borderRadius: 4
            }}
          >
            <CardContent>
              <Typography
                variant="h5"
                align="center"
                sx={{
                  mt: 6,
                  display: 'flex',
                  justifyContent: 'center',
                  alignItems: 'center',
                  fontSize: '200%'
                }}
              >
                {current.key}
              </Typography>
            </CardContent>
          </Card>

          <Card
            sx={{
              position: 'absolute',
              width: '100%',
              height: '100%',
              backfaceVisibility: 'hidden',
              transform: 'rotateY(180deg)',
              boxShadow: 4,
              borderRadius: 4,
              bgcolor: '#1e272e',
              color: 'white'
            }}
          >
            <CardContent>
              <Typography
                variant="h6"
                sx={{
                  mt: 6,
                  display: 'flex',
                  justifyContent: 'center',
                  alignItems: 'center',
                  fontSize: '200%'
                }}
              >
                {current.value}
              </Typography>
            </CardContent>
          </Card>
        </motion.div>
      </motion.div>

      <Box mt={4} display="flex" gap={2}>
        <Button variant="contained" color="secondary" onClick={handlePrev} disabled={index === 0}>
          ⬅ Trước
        </Button>
        <Button variant="contained" color="primary" onClick={handleNext} disabled={index === total - 1}>
          Tiếp ➡
        </Button>
      </Box>

      <Typography variant="body2" mt={2} color="text.secondary">
        💡 Space để lật, ← / → để chuyển thẻ
      </Typography>
    </Box>
  );
};

const sampleData = [
  { key: 'Aberration', value: 'Sự sai lệch, khác thường' },
  { key: 'Benevolent', value: 'Nhân từ, nhân ái' },
  { key: 'Cacophony', value: 'Âm thanh chói tai' },
  { key: 'Diligent', value: 'Siêng năng, cần cù' }
];

export default function App() {
  return <FlashcardPage data={sampleData} />;
}
