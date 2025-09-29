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
  title: 'teacher-report',
  type: 'group',
  icon: icons.samplePage,
  children: [
    {
      id: "student-dashboard",
      title: "student-dashboard",
      type: "collapse",
      children: [{
        id: 'student-performance',
        title: 'assignments',
        type: 'item',
        url: '/submission',
        target: false
      },
      {
        id: 'teacher-grading',
        title: 'Grading',
        type: 'item',
        url: '/sample-page',
        target: false
      },
      {
        id: 'assignment-list',
        title: 'Assignments',
        type: 'item',
        url: '/assignment',
        target: false
      },
      {
        id: 'flashcard',
        title: 'Flashcard',
        type: 'item',
        url: '/flashcard',
        target: false
      }
      ]
    }
  ]

};

export default samplePage;
