import PropTypes from 'prop-types';

// material-ui
import Chip from '@mui/material/Chip';
import Grid from '@mui/material/Grid';
import Stack from '@mui/material/Stack';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';

// project-imports
import MainCard from 'components/MainCard';

// assets
import { ArrowRight, ArrowUp } from 'iconsax-reactjs';

// ==============================|| STATISTICS - ECOMMERCE CARD  ||============================== //

export default function AnalyticEcommerce({ color = 'primary', title, count, percentage, isLoss, extra }) {
  return (
    <MainCard contentSX={{ p: 2.25 }}>
      <Stack sx={{ gap: 0.5 }}>
        <Typography variant="h6" sx={{ color: 'text.secondary' }}>
          {title}
        </Typography>
        <Grid container sx={{ alignItems: 'center' }}>
          <Grid>
            <Typography variant="h4" color="inherit">
              {count}
            </Typography>
          </Grid>
          {percentage && (
            <Grid>
              <Chip
                variant="combined"
                color={color}
                icon={
                  <>
                    {!isLoss && <ArrowUp style={{ transform: 'rotate(45deg)' }} />}
                    {isLoss && <ArrowRight style={{ transform: 'rotate(45deg)' }} />}
                  </>
                }
                label={`${percentage}%`}
                sx={{ ml: 1.25, pl: 1, borderRadius: 1 }}
                size="small"
              />
            </Grid>
          )}
        </Grid>
      </Stack>
      <Box sx={{ pt: 2.25 }}>
        <Typography variant="caption" sx={{ color: 'text.secondary' }}>
          You made an extra{' '}
          <Typography variant="caption" sx={{ color: `${color || 'primary'}.main` }}>
            {extra}
          </Typography>{' '}
          this year
        </Typography>
      </Box>
    </MainCard>
  );
}

AnalyticEcommerce.propTypes = {
  color: PropTypes.string,
  title: PropTypes.string,
  count: PropTypes.string,
  percentage: PropTypes.number,
  isLoss: PropTypes.bool,
  extra: PropTypes.string
};
