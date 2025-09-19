import PropTypes from 'prop-types';
// material-ui
import Button from '@mui/material/Button';
import Link from '@mui/material/Link';
import Stack from '@mui/material/Stack';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';

// assets
import { Code1, Link1 } from 'iconsax-reactjs';

// ==============================|| COMPONENTS - BREADCRUMBS  ||============================== //

export default function ComponentHeader({ title, caption, directory, link }) {
  return (
    <Box sx={{ pl: { xs: 2, md: 2.5, xl: 8 } }}>
      <Stack sx={{ gap: 1.25 }}>
        <Typography variant="h2" sx={{ fontWeight: 700 }}>
          {title}
        </Typography>
        {caption && (
          <Typography variant="h6" sx={{ color: 'text.secondary' }}>
            {caption}
          </Typography>
        )}
        {directory && (
          <Typography variant="caption" sx={{ color: 'text.secondary' }}>
            <Stack direction="row" sx={{ gap: 1, alignItems: 'center' }}>
              <Code1 size={14} />
              {directory}
            </Stack>
          </Typography>
        )}
        {link && (
          <Box>
            <Button
              variant="contained"
              color="secondary"
              startIcon={<Link1 size={16} />}
              component={Link}
              href={link}
              target="_blank"
              sx={(theme) => ({
                fontWeight: 500,
                bgcolor: 'secondary.light',
                color: 'secondary.darker',
                '&:hover': {
                  color: 'secondary.lighter',
                  ...theme.applyStyles('dark', { bgcolor: 'secondary.200', color: 'secondary.darker' })
                }
              })}
            >
              Reference
            </Button>
          </Box>
        )}
      </Stack>
    </Box>
  );
}

ComponentHeader.propTypes = { title: PropTypes.string, caption: PropTypes.string, directory: PropTypes.string, link: PropTypes.string };
