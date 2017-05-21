type BarColor = 'red' | 'yellow' | 'blue' | 'green';

interface BarProps {
    title: string;
    actual: number;
    max: number;
    color: BarColor;
}
export default BarProps;