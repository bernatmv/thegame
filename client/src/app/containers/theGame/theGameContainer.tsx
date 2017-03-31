import * as React from 'react';
import { bindActionCreators } from 'redux';
import { connect } from 'react-redux';
import RootState from '../../reducers/state/rootState';
import AvatarSection from '../../components/avatarSection/avatarSection';
import RoomSection from '../../components/roomSection/roomSection';
import EnemySection from '../../components/enemySection/enemySection';
import ChatSection from '../../components/chatSection/chatSection';
import SystemSection from '../../components/systemSection/systemSection';
import TheGameProps from './theGameProps';
import * as ChatActions from '../../actions/chatActions';
import * as style from './theGameContainer.css';

@connect(mapStateToProps, mapDispatchToProps)
export class TheGame extends React.Component<TheGameProps, {}> {
  render() {
    const { chats, connectionStatus, userId, actions } = this.props;
    return (
      <div className={style.container}>
        <div className={style.container__game} >
          <AvatarSection />
          <RoomSection />
          <EnemySection />
        </div>
        <div className={style.container__system} >
          <ChatSection chats={chats} 
                      sendChat={actions.sendChat} 
                      connectionStatus={connectionStatus}
                      userId={userId} />
          <SystemSection userId={userId} />
        </div>
      </div>
    );
  }
}

function mapStateToProps(state: RootState) {
  return {
    chats: state.chats,
    connectionStatus: state.connectionStatus,
    userId: state.userId
  };
}

function mapDispatchToProps(dispatch) {
  return {
    actions: bindActionCreators(ChatActions as any, dispatch)
  };
}