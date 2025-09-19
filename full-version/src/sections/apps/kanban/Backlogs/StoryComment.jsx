import PropTypes from 'prop-types';

// material-ui
import Grid from '@mui/material/Grid';
import Stack from '@mui/material/Stack';
import Typography from '@mui/material/Typography';

// project-imports
import Dot from 'components/@extended/Dot';
import Avatar from 'components/@extended/Avatar';
import MainCard from 'components/MainCard';
import { ImagePath, getImageUrl } from 'utils/getImageUrl';

// ==============================|| KANBAN BACKLOGS - STORY COMMENT ||============================== //

export default function StoryComment({ comment, profile }) {
  return (
    <MainCard content={false} sx={(theme) => ({ p: 1.5, mt: 1.25, ...theme.applyStyles('light', { bgcolor: 'secondary.lighter' }) })}>
      <Grid container spacing={1.5}>
        <Grid size={12}>
          <Grid container wrap="nowrap" spacing={1} sx={{ alignItems: 'center' }}>
            <Grid>
              <Avatar
                sx={{ width: 24, height: 24 }}
                size="sm"
                alt="User 1"
                src={profile && profile.avatar && getImageUrl(`${profile.avatar}`, ImagePath.USERS)}
              />
            </Grid>
            <Grid size="grow">
              <Grid container spacing={1} sx={{ alignItems: 'center', justifyContent: 'space-between' }}>
                <Grid>
                  <Typography variant="subtitle1">{profile.name}</Typography>
                </Grid>
                <Grid>
                  <Stack direction="row" sx={{ gap: 0.5, alignItems: 'center' }}>
                    <Dot size={6} sx={{ mt: -0.25 }} color="secondary" />
                    <Typography variant="caption" color="secondary">
                      {profile.time}
                    </Typography>
                  </Stack>
                </Grid>
              </Grid>
            </Grid>
          </Grid>
        </Grid>
        <Grid sx={{ '&.MuiGrid-root': { pt: 1.5 } }} size={12}>
          <Typography variant="body2">{comment?.comment}</Typography>
        </Grid>
      </Grid>
    </MainCard>
  );
}

StoryComment.propTypes = { comment: PropTypes.any, profile: PropTypes.any };
