import * as React from 'react';
import CreateCharacterSectionProps from './createCharacterSectionProps';
import {Rating, Popup, Dropdown, Step, Segment, Input, Icon, Divider, Button, Header} from 'semantic-ui-react';
import PlayerDto from '../../../common/service/dtos/playerDto';
import TranslationConstants from '../../../common/constants/translationConstants';
import i18nService from '../../../common/service/i18nServiceImpl';
import * as style from './createCharacterSection.css';

interface CreateCharacterSectionState {
    model: PlayerDto;
}

export default class LoginSection extends React.Component<CreateCharacterSectionProps, CreateCharacterSectionState> {

    constructor(props?: CreateCharacterSectionProps, context?: any) {
        super(props, context);
        this.state = {
            model: null
        };
    }

    render(): JSX.Element {
        return <div>
            <Icon circular inverted name='address card' size='massive' />
            <Divider horizontal inverted>Create your character</Divider>
            <Segment inverted>
                Placeholder
            </Segment>
            <Segment.Group piled>
                <Segment color='red'>Top</Segment>
                <Segment color='blue'>Middle</Segment>
                <Segment inverted color='yellow'>Bottom</Segment>
                <Segment inverted secondary color='yellow'>Bottom</Segment>
                <Segment inverted tertiary color='yellow'>Bottom</Segment>
            </Segment.Group>
            <Button
                inverted
                color='red'
                content='Like'
                icon='heart'
                label={{ basic: true, inverted: true, color: 'red', pointing: 'left', content: '1,024' }} />
            <Button.Group labeled color='blue'>
                <Button basic icon='man' content='Male' />
                <Button icon='woman' content='Female' />
                <Button icon='intergender' content='Hermafrodita' />
                <Button icon='neuter' content='Neuter' />
                <Button icon='genderless' content='Genderless' />
                <Button icon='lemon' content='Lemon' />
                <Button icon='other gender' content='Other' />
            </Button.Group>
            <Input
                inverted
                icon='tags'
                iconPosition='left'
                label={{ tag: true, content: 'Add Tag' }}
                labelPosition='right'
                placeholder='Enter tags' />
            <Input
                action={{ color: 'teal', labelPosition: 'left', icon: 'cart', content: 'Checkout' }}
                actionPosition='left'
                placeholder='Search...'
                defaultValue='52.03' />
            <div>
                <Segment circular style={{width: 175, height: 175}}>
                    <Header as='h2'>
                        Sale!
                        <Header.Subheader>
                        $10.99
                        </Header.Subheader>
                    </Header>
                </Segment>
                <Segment circular inverted style={{width: 175, height: 175}}>
                    <Header as='h2' inverted>
                        Buy Now
                        <Header.Subheader>
                        $10.99
                        </Header.Subheader>
                    </Header>
                </Segment>
            </div>
            <Step.Group vertical>
                <Step completed>
                    <Icon name='truck' />
                    <Step.Content>
                    <Step.Title>Shipping</Step.Title>
                    <Step.Description>Choose your shipping options</Step.Description>
                    </Step.Content>
                </Step>

                <Step active>
                    <Icon name='credit card' />
                    <Step.Content title='Billing' description='Enter billing information' />
                </Step>

                <Step icon='info' title='Confirm Order' description='Verify order details' />
            </Step.Group>
            <Dropdown text='Add user' icon='add user' floating labeled button className='icon'>
                <Dropdown.Menu>
                    <Dropdown.Header content='People You Might Know' />
                    {friendOptions.map((option) => <Dropdown.Item key={option.value} {...option} />)}
                </Dropdown.Menu>
            </Dropdown>
            Inline
            <Header as='h4'>
                <Icon name='trophy' />
                <Header.Content>
                    Trending repos
                    {' '}
                    <Dropdown inline header='Adjust time span' options={options} defaultValue={options[0].value} />
                </Header.Content>
            </Header>
            searchbox with multiselection and flags in dropdown but not in pills and custom render for the pills
            <Dropdown placeholder='Select Country' fluid multiple search selection options={countryOptions} />
            with images in dropdown
            <Dropdown placeholder='Select Friend' fluid selection options={friendOptions} noResultsMessage='Try another search.' />
            <div>
                <Popup
                    trigger={<Icon circular name='heart' />}
                    content='Hello. This is a mini popup'
                    size='mini' />
                <Popup
                    trigger={<Icon circular name='heart' />}
                    content='Hello. This is a tiny popup'
                    size='tiny' />
                <Popup
                    trigger={<Icon circular name='heart' />}
                    content='Hello. This is a small popup'
                    position='bottom center'
                    size='small' />
                <Popup
                    trigger={<Icon circular name='heart' />}
                    content='Hello. This is a large popup'
                    size='large' />
                <Popup
                    trigger={<Icon circular name='heart' />}
                    content='Hello. This is a huge popup'
                    position='right center'
                    size='huge' />
            </div>
            <Rating icon='star' defaultRating={3} maxRating={4} />
            <Rating icon='heart' defaultRating={1} maxRating={3} size='huge' clearable/>
        </div>;
    }
}

const options = [
  {
    key: 'today',
    text: 'today',
    value: 'today',
    content: 'Today',
  },
  {
    key: 'this week',
    text: 'this week',
    value: 'this week',
    content: 'This Week',
  },
  {
    key: 'this month',
    text: 'this month',
    value: 'this month',
    content: 'This Month',
  }
];

const friendOptions = [
  {
    key: 'Jenny Hess',
    text: 'Jenny Hess',
    value: 'Jenny Hess',
    image: { avatar: true, src: '/assets/images/avatar/small/jenny.jpg' },
  },
  {
    key: 'Elliot Fu',
    text: 'Elliot Fu',
    value: 'Elliot Fu',
    image: { avatar: true, src: '/assets/images/avatar/small/elliot.jpg' },
  },
  {
    key: 'Stevie Feliciano',
    text: 'Stevie Feliciano',
    value: 'Stevie Feliciano',
    image: { avatar: true, src: '/assets/images/avatar/small/stevie.jpg' },
  },
  {
    key: 'Christian',
    text: 'Christian',
    value: 'Christian',
    image: { avatar: true, src: '/assets/images/avatar/small/christian.jpg' },
  },
  {
    key: 'Matt',
    text: 'Matt',
    value: 'Matt',
    image: { avatar: true, src: '/assets/images/avatar/small/matt.jpg' },
  },
  {
    key: 'Justen Kitsune',
    text: 'Justen Kitsune',
    value: 'Justen Kitsune',
    image: { avatar: true, src: '/assets/images/avatar/small/justen.jpg' },
  }
];

const countryOptions = [
  { key: 'af', value: 'af', flag: 'af', text: 'Afghanistan' },
  { key: 'ax', value: 'ax', flag: 'ax', text: 'Aland Islands' },
  { key: 'al', value: 'al', flag: 'al', text: 'Albania' },
  { key: 'dz', value: 'dz', flag: 'dz', text: 'Algeria' },
  { key: 'as', value: 'as', flag: 'as', text: 'American Samoa' },
  { key: 'ad', value: 'ad', flag: 'ad', text: 'Andorra' },
  { key: 'ao', value: 'ao', flag: 'ao', text: 'Angola' },
  { key: 'ai', value: 'ai', flag: 'ai', text: 'Anguilla' },
  { key: 'ag', value: 'ag', flag: 'ag', text: 'Antigua' },
  { key: 'ar', value: 'ar', flag: 'ar', text: 'Argentina' },
  { key: 'am', value: 'am', flag: 'am', text: 'Armenia' },
  { key: 'aw', value: 'aw', flag: 'aw', text: 'Aruba' },
  { key: 'au', value: 'au', flag: 'au', text: 'Australia' },
  { key: 'at', value: 'at', flag: 'at', text: 'Austria' },
  { key: 'az', value: 'az', flag: 'az', text: 'Azerbaijan' },
  { key: 'bs', value: 'bs', flag: 'bs', text: 'Bahamas' },
  { key: 'bh', value: 'bh', flag: 'bh', text: 'Bahrain' },
  { key: 'bd', value: 'bd', flag: 'bd', text: 'Bangladesh' },
  { key: 'bb', value: 'bb', flag: 'bb', text: 'Barbados' },
  { key: 'by', value: 'by', flag: 'by', text: 'Belarus' },
  { key: 'be', value: 'be', flag: 'be', text: 'Belgium' },
  { key: 'bz', value: 'bz', flag: 'bz', text: 'Belize' },
  { key: 'bj', value: 'bj', flag: 'bj', text: 'Benin' },
  { key: 'bm', value: 'bm', flag: 'bm', text: 'Bermuda' },
  { key: 'bt', value: 'bt', flag: 'bt', text: 'Bhutan' },
  { key: 'bo', value: 'bo', flag: 'bo', text: 'Bolivia' },
  { key: 'ba', value: 'ba', flag: 'ba', text: 'Bosnia' },
  { key: 'bw', value: 'bw', flag: 'bw', text: 'Botswana' },
  { key: 'bv', value: 'bv', flag: 'bv', text: 'Bouvet Island' },
  { key: 'br', value: 'br', flag: 'br', text: 'Brazil' },
  { key: 'vg', value: 'vg', flag: 'vg', text: 'British Virgin Islands' },
  { key: 'bn', value: 'bn', flag: 'bn', text: 'Brunei' }
];