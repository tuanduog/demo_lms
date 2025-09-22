import React from 'react';
import { ListItem, ListItemText, TextField, Chip, Typography } from '@mui/material';
import { useState } from 'react';
const QuestionItem = React.memo(({ question, scoreValue, onScoreChange, gradingStatus, index }) => {
  const [localScore, setLocalScore] = useState(scoreValue || '');
  return (
    <ListItem
      key={question.questionSubmissionId}
      alignItems="flex-start"
      sx={{ mb: 2 }}
      secondaryAction={
        question.isCorrect !== null ? (
          <Chip label={question.isCorrect ? 'Đúng' : 'Sai'} color={question.isCorrect ? 'success' : 'error'} size="small" />
        ) : gradingStatus === 'pending' ? (
          <TextField
            type="number"
            placeholder="score here"
            value={localScore}
            onChange={(e) => setLocalScore(e.target.value)}
            onBlur={() => onScoreChange(localScore, question.questionSubmissionId)}
            size="small"
            variant="outlined"
          />
        ) : (
          <TextField type="number" placeholder="score here" value={localScore} size="small" variant="outlined" />
        )
      }
    >
      <ListItemText
        primary={
          <Typography variant="subtitle1" fontWeight="bold">
            Câu {index + 1}: {question.questionContent}
          </Typography>
        }
        secondary={
          <Typography variant="body2" color="text.primary">
            Trả lời: {question.studentAnswer}
          </Typography>
        }
      />
    </ListItem>
  );
});

export default QuestionItem;
