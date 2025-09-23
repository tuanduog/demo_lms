/*** This is example of menu item without group for horizontal layout. There will be no children. ***/

// assets
import { DocumentCode2 } from 'iconsax-reactjs';

// icons
const icons = {
  samplePage: DocumentCode2
};

// ==============================|| MENU ITEMS - SAMPLE PAGE ||============================== //

const samplePage = {
  id: 'main-topics',
  title: 'heheh',
  type: 'group',
  icon: icons.samplePage,
  children: [
    {
      id: 'sample-page',
      title: 'sample-page',
      type: 'item',
      url: '/sample-page',
      icon: icons.samplePage,
    }
  ]
};

export default samplePage;
