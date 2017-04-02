import * as React from 'react';
import RoomSectionProps from './roomSectionProps';
import * as classnames from 'classnames';
import * as style from './roomSection.css';

export default class RoomSection extends React.Component<RoomSectionProps, {}> {
    render(): JSX.Element {
        return (
            <div className={style.room}>
                <div className={style.row}>
                    <div className={style.row__column} />
                    <div className={style.row__column} />
                    <div className={style.row__column}>
                        <div className={style.tile__move}>
                            North
                        </div>
                    </div>
                    <div className={style.row__column}>
                        <div className={style.tile__move}>
                            Up
                        </div>
                    </div>
                    <div className={style.row__column} />
                </div>
                <div className={classnames(style.row, style.row__middle)}>
                    <div className={style.row__column}>
                        <div className={style.row__middle__lateral} />
                        <div className={style.row__middle__lateral}>
                            <div className={style.tile__move}>
                                West
                            </div>
                        </div>
                        <div className={style.row__middle__lateral} />
                    </div>
                    <div className={classnames(style.row__column, style.row__column__middle)}>
                        <div className={style.tile__room}>
                            Room
                        </div>
                    </div>
                    <div className={style.row__column}>
                        <div className={style.row__middle__lateral} />
                        <div className={style.row__middle__lateral}>
                            <div className={style.tile__move}>
                                East
                            </div>
                        </div>
                        <div className={style.row__middle__lateral} />
                    </div>
                </div>
                <div className={style.row}>
                    <div className={style.row__column} />
                    <div className={style.row__column} />
                    <div className={style.row__column}>
                        <div className={style.tile__move}>
                            South
                        </div>
                    </div>
                    <div className={style.row__column}>
                        <div className={style.tile__move}>
                            Down
                        </div>
                    </div>
                    <div className={style.row__column} />
                </div>
            </div>
        );
    }
}