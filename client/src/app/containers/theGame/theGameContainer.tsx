import * as React from 'react';
import { bindActionCreators } from 'redux';
import { connect } from 'react-redux';
import RootState from '../../reducers/state/rootState';
import { MainSection } from '../../components/mainSection/mainSection';
import TheGameProps from './theGameProps';
import * as ChatActions from '../../actions/chatActions';

@connect(mapStateToProps, mapDispatchToProps)
export class TheGame extends React.Component<TheGameProps, {}> {
  render() {
    const { chats, actions } = this.props;
    return (
      <div>
        <MainSection chats={chats} sendChat={actions.sendChat} />
      </div>
    );
  }
}

function mapStateToProps(state: RootState) {
  return {
    chats: state.chats
  };
}

function mapDispatchToProps(dispatch) {
  return {
    actions: bindActionCreators(ChatActions as any, dispatch)
  };
}