import * as React from 'react';
import CreateCharacterProps from './createCharacterProps';
import {Rating, Dropdown, Segment, Input, Icon, Divider, Button, Header, Image} from 'semantic-ui-react';
import PlayerDto from '../../../../common/service/dtos/playerDto';
import TranslationConstants from '../../../../common/constants/translationConstants';
import i18nService from '../../../../common/service/i18nServiceImpl';
import * as styles from './createCharacter.css';
import * as animations from '../../../../css/animations.css';
import * as humanImage from '../../../../../assets/images/goblin-head.svg';
import * as warriorImage from '../../../../../assets/images/chicken.svg';

export default class Login extends React.Component<CreateCharacterProps, CreateCharacterState> {

    constructor(props?: CreateCharacterProps, context?: any) {
        super(props, context);
        this.state = {
            model: null
        };
    }

    render(): JSX.Element {
        return <div className={styles.signup}>
            <Divider horizontal inverted section style={{fontSize: 11}}>Estás a punto de renacer</Divider>
            <Header as='h4' inverted>
                <Icon name='users' inverted circular />
                <Header.Content>
                    En esta iteración de tu vida serás un 
                    {' '}
                    <Dropdown inline options={races} defaultValue={races[0].value} style={{borderBottom: 'dashed 1px white'}} />
                </Header.Content>
            </Header>
            <div>
                <Rating icon='heart' defaultRating={3} maxRating={5} disabled />
                {' '}
                <Rating icon='star' defaultRating={3} maxRating={5} disabled />
            </div>
            <Divider horizontal inverted section style={{fontSize: 11}}>Te conocerán cómo</Divider>
            <Input
                icon={<Icon name='heart' inverted />}
                iconPosition='left'
                label={{icon: 'check', color: 'green'}}
                labelPosition='right corner'
                placeholder='¿Cómo te llamarás?' />
            <Divider horizontal inverted section style={{fontSize: 11}}>El legendario</Divider>
            <Dropdown options={professions} defaultValue={professions[0].value} style={{borderBottom: 'dashed 1px white'}} />
            <Divider horizontal inverted section style={{fontSize: 11}}>Serás mucho más que tu género</Divider>
            <div>
                <Button.Group labeled inverted vertical color='green'>
                    <Button icon='man' content='Hombre' />
                    <Button icon='woman' content='Mujer' />
                    <Button icon='intergender' content='Hermafrodita' />
                </Button.Group>
                {' '}
                <Button.Group labeled inverted vertical color='green'>
                    <Button icon='neuter' content='Neutro' />
                    <Button icon='genderless' content='Sin género' />
                    <Button icon='other gender' content='Otro' />
                </Button.Group>
            </div>
            <Divider horizontal inverted section style={{fontSize: 11}}>¡RESPIRA!</Divider>
            <Button inverted circular icon='heart' size='massive' color='green' className={animations.beat} />
        </div>;
    }
}

interface CreateCharacterState {
    model: PlayerDto;
}

const races = [
    {
        key: 'human',
        text: 'humano',
        value: 'human',
        content: <Segment inverted><Header inverted size='small' color='grey'><Image src={humanImage} size='mini' color />{' '}Humano</Header></Segment>
    }
];

const professions = [
    {
        key: 'warrior',
        text: 'Guerrero',
        value: 'warrior',
        content: <Segment inverted><Header inverted size='small' color='grey'><Image src={warriorImage} size='mini' color />{' '}Guerrero</Header></Segment>
    }
];