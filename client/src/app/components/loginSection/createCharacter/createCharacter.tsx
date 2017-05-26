import * as React from 'react';
import CreateCharacterProps from './createCharacterProps';
import {Dropdown, Segment, Input, Icon, Divider, Button, Header, Image} from 'semantic-ui-react';
import GameConstants from '../../../../common/constants/gameConstants';
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
            race: GameConstants.Human,
            name: '',
            validName: false,
            profession: GameConstants.Warrior,
            gender: null
        };
    }

    private _onChangeRace = (event, data) => this.setState({race: data.value});
    private _onChangeProfession = (event, data) => this.setState({profession: data.value});
    private _onChangeGender = (event, data) => this.setState({gender: data.value});
    private _onChangeName = (event, data) => {
        this.setState({name: data.value});
        // TODO: checlk with server name availability
        this.setState({validName: !!data.value});
    };

    private _getFinishSection = () => {
        return (this.state.race && this.state.validName && this.state.profession && this.state.gender)
                ? <div>
                    <Divider horizontal inverted section style={{fontSize: 11}}>
                        {i18nService.Instance.translate(TranslationConstants.signUpFinishTitle)}
                    </Divider>
                    <Button 
                        inverted 
                        circular 
                        icon='heart' 
                        size='huge' 
                        color='green' 
                        className={animations.beat} />
                </div>
                : null;
    };

    render(): JSX.Element {
        let raceImage = (this.state.race === GameConstants.Goblin) ? goblinImage : humanImage;
        return <div className={styles.signup}>
            
            <Divider horizontal inverted section style={{fontSize: 11}}>
                {i18nService.Instance.translate(TranslationConstants.signUpTitle)}
            </Divider>
            <Header as='h4' inverted>
                <Image src={raceImage} size='mini' shape='circular' />
                {' '}
                <Header.Content>
                    {i18nService.Instance.translate(TranslationConstants.signUpRaceLabel)}
                    {' '}
                    <Dropdown 
                        inline 
                        options={races} 
                        defaultValue={races[0].value} 
                        onChange={this._onChangeRace}
                        className={styles.signup__dropdown} />
                </Header.Content>
            </Header>

            <Divider horizontal inverted section style={{fontSize: 11}}>
                {i18nService.Instance.translate(TranslationConstants.signUpNameLabel)}
            </Divider>
            <Input
                icon={<Icon name='pencil' inverted />}
                iconPosition='left'
                label={this.state.validName ? {icon: 'check', color: 'green'} : null}
                labelPosition={this.state.validName ? 'right corner' : null}
                onChange={this._onChangeName}
                placeholder={i18nService.Instance.translate(TranslationConstants.signUpNamePlaceholder)} />

            <Divider horizontal inverted section style={{fontSize: 11}}>
                {i18nService.Instance.translate(TranslationConstants.signUpProfessionTitle)}
            </Divider>
            <Dropdown 
                options={professions} 
                defaultValue={professions[0].value} 
                onChange={this._onChangeProfession}
                className={styles.signup__dropdown} />

            <Divider horizontal inverted section style={{fontSize: 11}}>
                {i18nService.Instance.translate(TranslationConstants.signUpGenderTitle)}
            </Divider>
            <div>
                <Button.Group labeled inverted vertical color='green'>
                    <Button 
                        active={this.state.gender === GameConstants.Male}
                        value={GameConstants.Male} 
                        icon='man' 
                        content={i18nService.Instance.translate(TranslationConstants.genderMale)} 
                        onClick={this._onChangeGender}/>
                    <Button 
                        active={this.state.gender === GameConstants.Female}
                        value={GameConstants.Female} 
                        icon='woman' 
                        content={i18nService.Instance.translate(TranslationConstants.genderFemale)} 
                        onClick={this._onChangeGender}/>
                    <Button 
                        active={this.state.gender === GameConstants.Intergender}
                        value={GameConstants.Intergender} 
                        icon='intergender' 
                        content={i18nService.Instance.translate(TranslationConstants.genderIntergender)} 
                        onClick={this._onChangeGender}/>
                </Button.Group>
                {' '}
                <Button.Group labeled inverted vertical color='green'>
                    <Button 
                        active={this.state.gender === GameConstants.Neuter}
                        value={GameConstants.Neuter}
                        icon='neuter'
                        content={i18nService.Instance.translate(TranslationConstants.genderNeuter)} 
                        onClick={this._onChangeGender}/>
                    <Button 
                        active={this.state.gender === GameConstants.Genderless}
                        value={GameConstants.Genderless}
                        icon='genderless'
                        content={i18nService.Instance.translate(TranslationConstants.genderGenderless)} 
                        onClick={this._onChangeGender}/>
                    <Button 
                        active={this.state.gender === GameConstants.OtherGender}
                        value={GameConstants.OtherGender}
                        icon='other gender'
                        content={i18nService.Instance.translate(TranslationConstants.genderOther)} 
                        onClick={this._onChangeGender}/>
                </Button.Group>
            </div>

            {this._getFinishSection()}
        </div>;
    }
}

interface CreateCharacterState {
    race: string;
    name: string;
    validName: boolean;
    profession: string;
    gender: string;
}

// TODO: we have to get race and profession from each dto in the assets once we have it defined
const races = [
    {
        key: GameConstants.Human,
        text: i18nService.Instance.translate(TranslationConstants.raceHuman).toLocaleLowerCase(),
        value: GameConstants.Human,
        content: <Segment inverted className={styles.signup__dropdown__segment}>
                    <Header inverted size='small' color='grey'>
                        <Image src={humanImage} size='mini' shape='circular' />
                        {' '}{i18nService.Instance.translate(TranslationConstants.raceHuman)}
                    </Header>
                </Segment>
    },
    {
        key: GameConstants.Goblin,
        text: i18nService.Instance.translate(TranslationConstants.raceGoblin).toLocaleLowerCase(),
        value: GameConstants.Goblin,
        content: <Segment inverted className={styles.signup__dropdown__segment}>
                    <Header inverted size='small' color='grey'>
                        <Image src={goblinImage} size='mini' shape='circular' />
                        {' '}{i18nService.Instance.translate(TranslationConstants.raceGoblin)}
                    </Header>
                </Segment>
    }
];

// TODO: we have to get race and profession from each dto in the assets once we have it defined
const professions = [
    {
        key: GameConstants.Warrior,
        text: i18nService.Instance.translate(TranslationConstants.professionWarrior).toLocaleLowerCase(),
        value: GameConstants.Warrior,
        content: <Segment inverted className={styles.signup__dropdown__segment}>
                    <Header inverted size='small' color='grey'>
                        <Image src={warriorImage} size='mini' shape='circular' />
                        {' '}{i18nService.Instance.translate(TranslationConstants.professionWarrior)}
                    </Header>
                </Segment>
    },
    {
        key: GameConstants.Wizard,
        text: i18nService.Instance.translate(TranslationConstants.professionWizard).toLocaleLowerCase(),
        value: GameConstants.Wizard,
        content: <Segment inverted className={styles.signup__dropdown__segment}>
                    <Header inverted size='small' color='grey'>
                        <Image src={wizardImage} size='mini' shape='circular' />
                        {' '}{i18nService.Instance.translate(TranslationConstants.professionWizard)}
                    </Header>
                </Segment>
    }
];