import * as React from 'react';
import { Image, Progress } from 'semantic-ui-react';
import AvatarSectionProps from './avatarSectionProps';
import * as avatarImage from '../../../../assets/goblin_512x512_desaturated.png';
import * as style from './avatarSection.css';

export default class AvatarSection extends React.Component<AvatarSectionProps, {}> {
    render(): JSX.Element {
        return (
            <div className={style.container__game__avatar}>
                <div className={style.container__game__avatar__profile}>
                    <Image src={avatarImage} className={style.container__game__avatar__profile__image} />
                    <div className={style.container__game__avatar__profile__status}>
                        <div>┌<b>Morgobor</b>┘</div>
                        <div>Hacker ─ Lvl 1</div>
                        <div className={style.container__game__avatar__profile__status__row}>
                            <div className={style.status__row__title}>HP</div> 
                            <Progress className={style.status__row__progress} color={'red'} value={100} total={100} progress={'ratio'} inverted={'true'} />
                        </div>
                        <div className={style.container__game__avatar__profile__status__row}>
                            <div className={style.status__row__title}>MP</div> 
                            <Progress className={style.status__row__progress} color={'blue'} value={70} total={100} progress={'ratio'} inverted={'true'} />
                        </div>
                        <div className={style.container__game__avatar__profile__status__row}>
                            <div className={style.status__row__title}>SP</div> 
                            <Progress className={style.status__row__progress} color={'yellow'} value={30} total={100} progress={'ratio'} inverted={'true'} />
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}