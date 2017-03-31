import * as React from 'react';
import { Image } from 'semantic-ui-react';
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
                        <div><b>Morgobor</b></div>
                        <br/>
                        <div>HP: 100 / 100</div>
                        <div>MP: 100 / 100</div>
                        <div>SP: 100 / 100</div>
                    </div>
                </div>
            </div>
        );
    }
}