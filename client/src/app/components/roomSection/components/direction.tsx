import * as React from 'react';
import {Tooltip} from 'react-lightweight-tooltip';
import DirectionProps from './directionProps';
import * as style from './direction.css';

const tooltipStyle = {
    wrapper: {
        cursor: 'pointer',
        color: '#888'
    },
    gap: {},
    content: {
        backgroundColor: '#161616',
        color: '#fff',
        cursor: 'pointer',
        fontSize: '12px'
    },
    tooltip: {
        backgroundColor: '#161616',
        borderRadius: '5px'
    },
    arrow: {
        borderTop: 'solid #161616 5px'
    }
};

export default class Direction extends React.Component<DirectionProps, {}> {
    static defaultProps = {
        nextRoom: {
            title: '? ? ?',
            shortDescription: 'Aún no has visitado esta ubicación!'
        }
    };

    _move = () => {
        this.props.move();
    }

    render(): JSX.Element { //TODO: add animation on transition
        return (
            <div className={style.tile__move} onClick={this._move}>
                <div className={style.tile__move__title}>
                    {this.props.title}
                </div>
                <div className={style.tile__move__description}>
                    <Tooltip content={this.props.nextRoom.shortDescription} styles={tooltipStyle}>
                        {this.props.nextRoom.title}
                    </Tooltip>
                </div>                
            </div>
        );
    }
}