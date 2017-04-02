interface StatusProperty {
    current: number;
    total: number;
    hide?: boolean;
}

interface ProfileProps {
    name?: string;
    race: string;
    profession?: string;
    level: number;
    hp: StatusProperty;
    mp: StatusProperty;
    sp: StatusProperty;
    size?: 'small' | 'medium';
}
export default ProfileProps;