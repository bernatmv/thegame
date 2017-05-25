import * as React from 'react';
import CreateCharacterProps from './createCharacterProps';
import {Dropdown, Segment, Input, Icon, Divider, Button, Header, Image} from 'semantic-ui-react';
import PlayerDto from '../../../../common/service/dtos/playerDto';
import TranslationConstants from '../../../../common/constants/translationConstants';
import i18nService from '../../../../common/service/i18nServiceImpl';
import * as styles from './createCharacter.css';
import * as animations from '../../../../css/animations.css';
import * as humanImage from '../../../../../assets/images/human-head.svg';
import * as goblinImage from '../../../../../assets/images/goblin-head.svg';
import * as wizardImage from '../../../../../assets/images/wizard.svg';
import * as warriorImage from '../../../../../assets/images/warrior.svg';

export default class Login extends React.Component<CreateCharacterProps, CreateCharacterState> {

    constructor(props?: CreateCharacterProps, context?: any) {
        super(props, context);
        this.state = {
            model: null
        };
    }

    render(): JSX.Element {
        return <div className={styles.signup}>
            
            <Divider horizontal inverted section style={{fontSize: 11}}>
                {i18nService.Instance.translate(TranslationConstants.signUpTitle)}
            </Divider>
            <Header as='h4' inverted>
                <Icon 
                    name='pencil' 
                    inverted circular />
                <Header.Content>
                    {i18nService.Instance.translate(TranslationConstants.signUpRaceLabel)}
                    {' '}
                    <Dropdown 
                        inline 
                        options={races} 
                        defaultValue={races[0].value} 
                        style={inlineDropdownStyle} />
                </Header.Content>
            </Header>

            <Divider horizontal inverted section style={{fontSize: 11}}>
                {i18nService.Instance.translate(TranslationConstants.signUpNameLabel)}
            </Divider>
            <Input
                icon={<Icon name='heart' inverted />}
                iconPosition='left'
                label={{icon: 'check', color: 'green'}}
                labelPosition='right corner'
                placeholder={i18nService.Instance.translate(TranslationConstants.signUpNamePlaceholder)} />

            <Divider horizontal inverted section style={{fontSize: 11}}>
                {i18nService.Instance.translate(TranslationConstants.signUpProfessionTitle)}
            </Divider>
            <Dropdown 
                options={professions} 
                defaultValue={professions[0].value} 
                style={inlineDropdownStyle} />

            <Divider horizontal inverted section style={{fontSize: 11}}>
                {i18nService.Instance.translate(TranslationConstants.signUpGenderTitle)}
            </Divider>
            <div>
                <Button.Group labeled inverted vertical color='green'>
                    <Button icon='man'          content={i18nService.Instance.translate(TranslationConstants.genderMale)} />
                    <Button icon='woman'        content={i18nService.Instance.translate(TranslationConstants.genderFemale)} />
                    <Button icon='intergender'  content={i18nService.Instance.translate(TranslationConstants.genderIntergender)} />
                </Button.Group>
                {' '}
                <Button.Group labeled inverted vertical color='green'>
                    <Button icon='neuter'       content={i18nService.Instance.translate(TranslationConstants.genderNeuter)} />
                    <Button icon='genderless'   content={i18nService.Instance.translate(TranslationConstants.genderGenderless)} />
                    <Button icon='other gender' content={i18nService.Instance.translate(TranslationConstants.genderOther)} />
                </Button.Group>
            </div>

            <Divider horizontal inverted section style={{fontSize: 11}}>
                {i18nService.Instance.translate(TranslationConstants.signUpFinishTitle)}
            </Divider>
            <Button 
                inverted 
                circular 
                icon='heart' 
                size='massive' 
                color='green' 
                className={animations.beat} />
        </div>;
    }
}

interface CreateCharacterState {
    model: PlayerDto;
}

const inlineDropdownStyle = {borderBottom: 'dashed 1px white'};

// TODO: we have to get race and profession from each dto in the assets once we have it defined
const races = [
    {
        key: 'human',
        text: i18nService.Instance.translate(TranslationConstants.raceHuman).toLocaleLowerCase(),
        value: 'human',
        content: <Segment inverted>
                    <Header inverted size='small' color='grey'>
                        <Image src={humanImage} size='mini' color />
                        {' '}{i18nService.Instance.translate(TranslationConstants.raceHuman)}
                    </Header>
                </Segment>
    },
    {
        key: 'goblin',
        text: i18nService.Instance.translate(TranslationConstants.raceGoblin).toLocaleLowerCase(),
        value: 'goblin',
        content: <Segment inverted>
                    <Header inverted size='small' color='grey'>
                        <Image src={goblinImage} size='mini' color />
                        {' '}{i18nService.Instance.translate(TranslationConstants.raceGoblin)}
                    </Header>
                </Segment>
    }
];

// TODO: we have to get race and profession from each dto in the assets once we have it defined
const professions = [
    {
        key: 'warrior',
        text: i18nService.Instance.translate(TranslationConstants.professionWarrior).toLocaleLowerCase(),
        value: 'warrior',
        content: <Segment inverted>
                    <Header inverted size='small' color='grey'>
                        <Image src={warriorImage} size='mini' color />
                        {' '}{i18nService.Instance.translate(TranslationConstants.professionWarrior)}
                    </Header>
                </Segment>
    },
    {
        key: 'wizard',
        text: i18nService.Instance.translate(TranslationConstants.professionWizard).toLocaleLowerCase(),
        value: 'wizard',
        content: <Segment inverted>
                    <Header inverted size='small' color='grey'>
                        <Image src={wizardImage} size='mini' color />
                        {' '}{i18nService.Instance.translate(TranslationConstants.professionWizard)}
                    </Header>
                </Segment>
    }
];