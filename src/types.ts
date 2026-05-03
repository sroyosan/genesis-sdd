export type SectionId = 'vision' | 'requirements' | 'tech_stack' | 'data_model' | 'endpoints' | 'ux_flow' | 'components';

export interface SpecSection {
  id: SectionId;
  title: string;
  content: string;
  status: 'empty' | 'draft' | 'complete';
}

export interface SDD {
  projectName: string;
  sections: Record<SectionId, SpecSection>;
  lastUpdated: number;
}

export interface Message {
  role: 'user' | 'model' | 'system';
  content: string;
}

export const INITIAL_SDD: SDD = {
  projectName: 'Nuevo Proyecto',
  sections: {
    vision: { id: 'vision', title: 'Vision & Purpose', content: '', status: 'empty' },
    requirements: { id: 'requirements', title: 'Functional Requirements', content: '', status: 'empty' },
    tech_stack: { id: 'tech_stack', title: 'Tech Stack', content: '', status: 'empty' },
    data_model: { id: 'data_model', title: 'Data Model & Schema', content: '', status: 'empty' },
    endpoints: { id: 'endpoints', title: 'API Endpoints', content: '', status: 'empty' },
    ux_flow: { id: 'ux_flow', title: 'User Experience Flow', content: '', status: 'empty' },
    components: { id: 'components', title: 'Main Components', content: '', status: 'empty' },
  },
  lastUpdated: Date.now(),
};
