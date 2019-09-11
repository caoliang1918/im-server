// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ImProtocol.proto

package org.zhongweixian.live.protocol;

/**
 * Protobuf enum {@code src.main.proto.Command}
 */
public enum Command
    implements com.google.protobuf.ProtocolMessageEnum {
  /**
   * <code>CMD_LOGIN = 0;</code>
   */
  CMD_LOGIN(0),
  /**
   * <pre>
   *登录响应
   * </pre>
   *
   * <code>CMD_LOGIN_RESP = 1;</code>
   */
  CMD_LOGIN_RESP(1),
  /**
   * <pre>
   *握手请求，含http的websocket握手请求
   * </pre>
   *
   * <code>CMD_PING = 2;</code>
   */
  CMD_PING(2),
  /**
   * <pre>
   *鉴权请求
   * </pre>
   *
   * <code>CMD_AUTH = 3;</code>
   */
  CMD_AUTH(3),
  /**
   * <pre>
   * 发送响应成功消息
   * </pre>
   *
   * <code>CMD_SEND_SUCCESS = 4;</code>
   */
  CMD_SEND_SUCCESS(4),
  /**
   * <pre>
   *申请进入群组
   * </pre>
   *
   * <code>CMD_JOIN_GROUP = 5;</code>
   */
  CMD_JOIN_GROUP(5),
  /**
   * <pre>
   *申请入群管理
   * </pre>
   *
   * <code>CMD_JOIN_GROUP_MANAGER = 6;</code>
   */
  CMD_JOIN_GROUP_MANAGER(6),
  /**
   * <pre>
   *进入群组通知
   * </pre>
   *
   * <code>CMD_JOIN_GROUP_NOTIFY = 7;</code>
   */
  CMD_JOIN_GROUP_NOTIFY(7),
  /**
   * <pre>
   *退出群组通知
   * </pre>
   *
   * <code>CMD_EXIT_GROUP_NOTIFY = 8;</code>
   */
  CMD_EXIT_GROUP_NOTIFY(8),
  /**
   * <pre>
   *群聊
   * </pre>
   *
   * <code>CMD_GROUP_CHAT = 9;</code>
   */
  CMD_GROUP_CHAT(9),
  /**
   * <pre>
   *群聊
   * </pre>
   *
   * <code>CMD_GROUP_CHAT_SECURITY = 10;</code>
   */
  CMD_GROUP_CHAT_SECURITY(10),
  /**
   * <pre>
   *单聊
   * </pre>
   *
   * <code>CMD_CHAT = 11;</code>
   */
  CMD_CHAT(11),
  /**
   * <pre>
   *单聊
   * </pre>
   *
   * <code>CMD_CHAT_SECURITY = 12;</code>
   */
  CMD_CHAT_SECURITY(12),
  /**
   * <pre>
   *删除回话列表
   * </pre>
   *
   * <code>CMD_TALK_LIST_CLOSE = 13;</code>
   */
  CMD_TALK_LIST_CLOSE(13),
  /**
   * <pre>
   *发出撤消消息指令(管理员可以撤消所有人的消息，自己可以撤消自己的消息)
   * </pre>
   *
   * <code>CMD_CANCEL_MSG = 14;</code>
   */
  CMD_CANCEL_MSG(14),
  /**
   * <pre>
   *收到撤消消息指令
   * </pre>
   *
   * <code>CMD_CANCEL_MSG_RESP = 15;</code>
   */
  CMD_CANCEL_MSG_RESP(15),
  /**
   * <pre>
   *获取用户信息;
   * </pre>
   *
   * <code>CMD_GET_USER_INFO = 16;</code>
   */
  CMD_GET_USER_INFO(16),
  /**
   * <pre>
   *获取用户信息响应;
   * </pre>
   *
   * <code>CMD_GET_USER_INFO_RESP = 17;</code>
   */
  CMD_GET_USER_INFO_RESP(17),
  /**
   * <pre>
   *获取会话列表
   * </pre>
   *
   * <code>CMD_TALK_LIST = 200;</code>
   */
  CMD_TALK_LIST(200),
  /**
   * <pre>
   *响应会话列表
   * </pre>
   *
   * <code>CMD_TALK_LIST_RESP = 201;</code>
   */
  CMD_TALK_LIST_RESP(201),
  /**
   * <pre>
   *获取好友信息
   * </pre>
   *
   * <code>CMD_USER_INFO = 202;</code>
   */
  CMD_USER_INFO(202),
  /**
   * <pre>
   *好友信息响应
   * </pre>
   *
   * <code>CMD_USER_INFO_RESP = 203;</code>
   */
  CMD_USER_INFO_RESP(203),
  /**
   * <pre>
   *获取群组信息
   * </pre>
   *
   * <code>CMD_GROUP_INFO = 204;</code>
   */
  CMD_GROUP_INFO(204),
  /**
   * <pre>
   *群组信息响应
   * </pre>
   *
   * <code>CMD_GROUP_INFO_RESP = 205;</code>
   */
  CMD_GROUP_INFO_RESP(205),
  UNRECOGNIZED(-1),
  ;

  /**
   * <code>CMD_LOGIN = 0;</code>
   */
  public static final int CMD_LOGIN_VALUE = 0;
  /**
   * <pre>
   *登录响应
   * </pre>
   *
   * <code>CMD_LOGIN_RESP = 1;</code>
   */
  public static final int CMD_LOGIN_RESP_VALUE = 1;
  /**
   * <pre>
   *握手请求，含http的websocket握手请求
   * </pre>
   *
   * <code>CMD_PING = 2;</code>
   */
  public static final int CMD_PING_VALUE = 2;
  /**
   * <pre>
   *鉴权请求
   * </pre>
   *
   * <code>CMD_AUTH = 3;</code>
   */
  public static final int CMD_AUTH_VALUE = 3;
  /**
   * <pre>
   * 发送响应成功消息
   * </pre>
   *
   * <code>CMD_SEND_SUCCESS = 4;</code>
   */
  public static final int CMD_SEND_SUCCESS_VALUE = 4;
  /**
   * <pre>
   *申请进入群组
   * </pre>
   *
   * <code>CMD_JOIN_GROUP = 5;</code>
   */
  public static final int CMD_JOIN_GROUP_VALUE = 5;
  /**
   * <pre>
   *申请入群管理
   * </pre>
   *
   * <code>CMD_JOIN_GROUP_MANAGER = 6;</code>
   */
  public static final int CMD_JOIN_GROUP_MANAGER_VALUE = 6;
  /**
   * <pre>
   *进入群组通知
   * </pre>
   *
   * <code>CMD_JOIN_GROUP_NOTIFY = 7;</code>
   */
  public static final int CMD_JOIN_GROUP_NOTIFY_VALUE = 7;
  /**
   * <pre>
   *退出群组通知
   * </pre>
   *
   * <code>CMD_EXIT_GROUP_NOTIFY = 8;</code>
   */
  public static final int CMD_EXIT_GROUP_NOTIFY_VALUE = 8;
  /**
   * <pre>
   *群聊
   * </pre>
   *
   * <code>CMD_GROUP_CHAT = 9;</code>
   */
  public static final int CMD_GROUP_CHAT_VALUE = 9;
  /**
   * <pre>
   *群聊
   * </pre>
   *
   * <code>CMD_GROUP_CHAT_SECURITY = 10;</code>
   */
  public static final int CMD_GROUP_CHAT_SECURITY_VALUE = 10;
  /**
   * <pre>
   *单聊
   * </pre>
   *
   * <code>CMD_CHAT = 11;</code>
   */
  public static final int CMD_CHAT_VALUE = 11;
  /**
   * <pre>
   *单聊
   * </pre>
   *
   * <code>CMD_CHAT_SECURITY = 12;</code>
   */
  public static final int CMD_CHAT_SECURITY_VALUE = 12;
  /**
   * <pre>
   *删除回话列表
   * </pre>
   *
   * <code>CMD_TALK_LIST_CLOSE = 13;</code>
   */
  public static final int CMD_TALK_LIST_CLOSE_VALUE = 13;
  /**
   * <pre>
   *发出撤消消息指令(管理员可以撤消所有人的消息，自己可以撤消自己的消息)
   * </pre>
   *
   * <code>CMD_CANCEL_MSG = 14;</code>
   */
  public static final int CMD_CANCEL_MSG_VALUE = 14;
  /**
   * <pre>
   *收到撤消消息指令
   * </pre>
   *
   * <code>CMD_CANCEL_MSG_RESP = 15;</code>
   */
  public static final int CMD_CANCEL_MSG_RESP_VALUE = 15;
  /**
   * <pre>
   *获取用户信息;
   * </pre>
   *
   * <code>CMD_GET_USER_INFO = 16;</code>
   */
  public static final int CMD_GET_USER_INFO_VALUE = 16;
  /**
   * <pre>
   *获取用户信息响应;
   * </pre>
   *
   * <code>CMD_GET_USER_INFO_RESP = 17;</code>
   */
  public static final int CMD_GET_USER_INFO_RESP_VALUE = 17;
  /**
   * <pre>
   *获取会话列表
   * </pre>
   *
   * <code>CMD_TALK_LIST = 200;</code>
   */
  public static final int CMD_TALK_LIST_VALUE = 200;
  /**
   * <pre>
   *响应会话列表
   * </pre>
   *
   * <code>CMD_TALK_LIST_RESP = 201;</code>
   */
  public static final int CMD_TALK_LIST_RESP_VALUE = 201;
  /**
   * <pre>
   *获取好友信息
   * </pre>
   *
   * <code>CMD_USER_INFO = 202;</code>
   */
  public static final int CMD_USER_INFO_VALUE = 202;
  /**
   * <pre>
   *好友信息响应
   * </pre>
   *
   * <code>CMD_USER_INFO_RESP = 203;</code>
   */
  public static final int CMD_USER_INFO_RESP_VALUE = 203;
  /**
   * <pre>
   *获取群组信息
   * </pre>
   *
   * <code>CMD_GROUP_INFO = 204;</code>
   */
  public static final int CMD_GROUP_INFO_VALUE = 204;
  /**
   * <pre>
   *群组信息响应
   * </pre>
   *
   * <code>CMD_GROUP_INFO_RESP = 205;</code>
   */
  public static final int CMD_GROUP_INFO_RESP_VALUE = 205;


  public final int getNumber() {
    if (this == UNRECOGNIZED) {
      throw new java.lang.IllegalArgumentException(
          "Can't get the number of an unknown enum value.");
    }
    return value;
  }

  /**
   * @deprecated Use {@link #forNumber(int)} instead.
   */
  @java.lang.Deprecated
  public static Command valueOf(int value) {
    return forNumber(value);
  }

  public static Command forNumber(int value) {
    switch (value) {
      case 0: return CMD_LOGIN;
      case 1: return CMD_LOGIN_RESP;
      case 2: return CMD_PING;
      case 3: return CMD_AUTH;
      case 4: return CMD_SEND_SUCCESS;
      case 5: return CMD_JOIN_GROUP;
      case 6: return CMD_JOIN_GROUP_MANAGER;
      case 7: return CMD_JOIN_GROUP_NOTIFY;
      case 8: return CMD_EXIT_GROUP_NOTIFY;
      case 9: return CMD_GROUP_CHAT;
      case 10: return CMD_GROUP_CHAT_SECURITY;
      case 11: return CMD_CHAT;
      case 12: return CMD_CHAT_SECURITY;
      case 13: return CMD_TALK_LIST_CLOSE;
      case 14: return CMD_CANCEL_MSG;
      case 15: return CMD_CANCEL_MSG_RESP;
      case 16: return CMD_GET_USER_INFO;
      case 17: return CMD_GET_USER_INFO_RESP;
      case 200: return CMD_TALK_LIST;
      case 201: return CMD_TALK_LIST_RESP;
      case 202: return CMD_USER_INFO;
      case 203: return CMD_USER_INFO_RESP;
      case 204: return CMD_GROUP_INFO;
      case 205: return CMD_GROUP_INFO_RESP;
      default: return null;
    }
  }

  public static com.google.protobuf.Internal.EnumLiteMap<Command>
      internalGetValueMap() {
    return internalValueMap;
  }
  private static final com.google.protobuf.Internal.EnumLiteMap<
      Command> internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<Command>() {
          public Command findValueByNumber(int number) {
            return Command.forNumber(number);
          }
        };

  public final com.google.protobuf.Descriptors.EnumValueDescriptor
      getValueDescriptor() {
    return getDescriptor().getValues().get(ordinal());
  }
  public final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptorForType() {
    return getDescriptor();
  }
  public static final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptor() {
    return org.zhongweixian.live.protocol.ImProtocol.getDescriptor()
        .getEnumTypes().get(1);
  }

  private static final Command[] VALUES = values();

  public static Command valueOf(
      com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
    if (desc.getType() != getDescriptor()) {
      throw new java.lang.IllegalArgumentException(
        "EnumValueDescriptor is not for this type.");
    }
    if (desc.getIndex() == -1) {
      return UNRECOGNIZED;
    }
    return VALUES[desc.getIndex()];
  }

  private final int value;

  private Command(int value) {
    this.value = value;
  }

  // @@protoc_insertion_point(enum_scope:src.main.proto.Command)
}
