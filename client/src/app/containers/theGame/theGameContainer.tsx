import * as React from 'react';
import { bindActionCreators } from 'redux';
import { connect } from 'react-redux';
import RootState from '../../reducers/state/rootState';
import LoginSection from '../../components/loginSection/loginSection';
import AvatarSection from '../../components/avatarSection/avatarSection';
import RoomSection from '../../components/roomSection/roomSection';
import EnemySection from '../../components/enemySection/enemySection';
import ChatSection from '../../components/chatSection/chatSection';
import SystemSection from '../../components/systemSection/systemSection';
import IntroSection from '../../components/introSection/introSection';
import TheGameProps from './theGameProps';
import * as ChatActions from '../../actions/chatActions';
import * as PlayerActions from '../../actions/playerActions';
import * as AuthActions from '../../actions/authActions';
import * as SystemActions from '../../actions/systemActions';
import * as style from './theGameContainer.css';
/*
import whyDidYouUpdate from 'why-did-you-update';

if (process.env.NODE_ENV !== 'production') {
  whyDidYouUpdate(React);
}
*/

@connect(mapStateToProps, mapDispatchToProps)
export class TheGame extends React.Component<TheGameProps, {}> {
  private _getLogin(connection, actions): JSX.Element {
    return <LoginSection login={actions.auth.login} />;
  }

  private _getBody(room, player, actions): JSX.Element {
    return <div className={style.container__game} >
      <AvatarSection room={room}
                  player={player} />
      <RoomSection room={room} 
                  move={actions.player.move} />
      <EnemySection room={room} />
    </div>;
  }

  private _getFooter(chats, connection, actions): JSX.Element {
    return <div className={style.container__system} >
      <ChatSection chats={chats} 
                  sendChat={actions.chat.sendChat} 
                  connectionStatus={connection.connectionStatus}
                  userId={connection.userId} />
      <SystemSection connection={connection} />
    </div>;
  }

  render() {
    const { chats, connection, room, player, system, actions } = this.props;
    if (player && room) {
      if (system.playedIntro) {
        return (
          <div className={style.container}>
            {this._getBody(room, player, actions)}
            {this._getFooter(chats, connection, actions)}
          </div>
        );
      } else {
        return <IntroSection endIntro={actions.system.endIntro} />;
      }
    } else {
      return (
        <div className={style.container}>
          {this._getLogin(connection, actions)}
        </div>
      );
    }
  }
}

function mapStateToProps(state: RootState) {
  return {
    chats: state.chats,
    connection: state.connection,
    room: state.room,
    player: state.player,
    system: state.system
  };
}

function mapDispatchToProps(dispatch) {
  return {
    actions: {
      chat: bindActionCreators(ChatActions as any, dispatch),
      player: bindActionCreators(PlayerActions as any, dispatch),
      auth: bindActionCreators(AuthActions as any, dispatch),
      system: bindActionCreators(SystemActions as any, dispatch)
    }
  };
}