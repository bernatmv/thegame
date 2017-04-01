import * as React from 'react';
import { Image, Progress } from 'semantic-ui-react';
import * as classnames from 'classnames';
import AvatarSectionProps from './avatarSectionProps';
//import * as avatarImage from '../../../../assets/goblin_512x512_desaturated.png';
import * as style from './avatarSection.css';

export default class AvatarSection extends React.Component<AvatarSectionProps, {}> {
    render(): JSX.Element {
        return (
            <div className={style.avatar}>
                <div className={style.avatar__profile}>
                    <div className={style.avatar__profile}>
                        <div className={classnames(style.profile__row, style.profile__row_title)}>
                            『<b>Morgobor</b>』
                        </div>
                        <div className={classnames(style.profile__row, style.profile__row_level)}>
                            「Hacker ― LV1」
                        </div>
                        <div className={style.profile__row}>
                            <div className={style.status__row__label}>HP</div> 
                            <Progress className={style.status__row__progress} color={'red'} value={100} total={100} progress={'ratio'} inverted={'true'} />
                        </div>
                        <div className={style.profile__row}>
                            <div className={style.status__row__label}>MP</div> 
                            <Progress className={style.status__row__progress} color={'blue'} value={70} total={100} progress={'ratio'} inverted={'true'} />
                        </div>
                        <div className={style.profile__row}>
                            <div className={style.status__row__label}>SP</div> 
                            <Progress className={style.status__row__progress} color={'yellow'} value={30} total={100} progress={'ratio'} inverted={'true'} />
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}