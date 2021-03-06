package com.java.core.netty.websocket.common;

import com.dragsun.websocket.adapter.KeepAliveHandlerAdapter;
import com.dragsun.websocket.annotation.WSRequestMapping;
import com.dragsun.websocket.cache.WebSocketCacheManager;
import com.dragsun.websocket.cache.WebSocketClient;
import com.dragsun.websocket.utils.MessageUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.*;

import java.util.Collection;
import java.util.Map;

/**
 *
 * 定位数据处理器
 * Created by zhuangjiesen on 2017/9/13.
 */

@WSRequestMapping(uri = "/location")
public class LocationHandlerAdapter extends KeepAliveHandlerAdapter<TextWebSocketFrame> {



    @Override
    public void handlerWebSocketFrameData(ChannelHandlerContext ctx, TextWebSocketFrame webSocketFrame) {
        System.out.println(" ---- LocationHandlerAdapter .....handlerWebSocketFrameData ....");


        String content = webSocketFrame.text();

        System.out.println("LocationHandlerAdapter ....content : " + content );


    }


    @Override
    public void handleResponse(Map<String, Object> params) {
        System.out.println(" ---- LocationHandlerAdapter .....handleResponse ....");

        String message = (String) params.get("message");
        WebSocketCacheManager wcm = applicationContext.getBean(WebSocketCacheManager.class);
        String uri = getUri();
        Collection<WebSocketClient> clients = wcm.getClientsByUri(uri);
        //批量发送数据
        MessageUtils.sendMessage(clients , message);


    }


    @Override
    public void onUpgradeCompleted(ChannelHandlerContext ctx, WebSocketClient webSocketClient) {

    }

}
