export interface Moderator {
    id: number;
    name: string;
    username: string;
    password: string;
    level: number;
    active: boolean;
  }

  export interface ModeratorApplication {
    id: number;
    name: string;
    username: string;
    password: string;
    accepted: boolean;
    reasonForApplying: string;
  }
