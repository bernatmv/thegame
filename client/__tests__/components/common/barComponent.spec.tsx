import * as React from 'react';
import { shallow } from 'enzyme';
import Bar from '../../../src/app/components/common/bar/bar';
import * as style from '../../../src/app/components/common/bar/bar.css';

const setup = () => {
    const props = {
        title: 'test title',
        actual: 500,
        max: 1000
    };

    const enzymeWrapper = shallow(<Bar {...props} color={'yellow'} />);

    return {
        props,
        enzymeWrapper
    };
};

describe('component >> Bar', () => {
    it('should render', () => {
        const { enzymeWrapper } = setup();

        expect(enzymeWrapper.length).toBe(1);
    });

    it('should render self and subcomponents', () => {
        const { enzymeWrapper } = setup();

        expect(enzymeWrapper.closest('div').hasClass(style.bar__container)).toBe(true);
        expect(enzymeWrapper.find('div')).toHaveLength(6);
    });
});

/*
describe('component >> Bar', () => {

    it('should render self and subcomponents', () => {
        const { enzymeWrapper } = setup();

        expect(enzymeWrapper.find('header').hasClass('header')).toBe(true)

        expect(enzymeWrapper.find('h1').text()).toBe('todos')

        const todoInputProps = enzymeWrapper.find('TodoTextInput').props()
        expect(todoInputProps.newTodo).toBe(true)
        expect(todoInputProps.placeholder).toEqual('What needs to be done?')
    })

    it('should call addTodo if length of text is greater than 0', () => {
        const { enzymeWrapper, props } = setup()
        const input = enzymeWrapper.find('TodoTextInput')
        input.props().onSave('')
        expect(props.addTodo.mock.calls.length).toBe(0)
        input.props().onSave('Use Redux')
        expect(props.addTodo.mock.calls.length).toBe(1)
    })
})

const setup = () => {
    const props = { //jest.fn() <== Stub
        title: 'test title',
        actual: 500,
        max: 1000,
        color: 'yellow'
    };

    const enzymeWrapper = shallow(<Bar {...props} />);

    return {
        props,
        enzymeWrapper
    };
};
*/