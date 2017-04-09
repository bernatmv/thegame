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
import * as PlayerActions from '../../actions/playerActions';
import * as style from './theGameContainer.css';

@connect(mapStateToProps, mapDispatchToProps)
export class TheGame extends React.Component<TheGameProps, {}> {
  render() {
    const { chats, connection, room, actions } = this.props;
    return (
      <div className={style.container}>
        <div className={style.container__game} >
          <AvatarSection room={room} />
          <RoomSection room={room} 
                      move={actions.player.move} />
          <EnemySection room={room} />
        </div>
        <div className={style.container__system} >
          <ChatSection chats={chats} 
                      sendChat={actions.chat.sendChat} 
                      connectionStatus={connection.connectionStatus}
                      userId={connection.userId} />
          <SystemSection userId={connection.userId} />
        </div>
      </div>
    );
  }
}

function mapStateToProps(state: RootState) {
  return {
    chats: state.chats,
    connection: state.connection,
    room: state.room
  };
}

function mapDispatchToProps(dispatch) {
  return {
    actions: {
      chat: bindActionCreators(ChatActions as any, dispatch),
      player: bindActionCreators(PlayerActions as any, dispatch)
    }
  };
}