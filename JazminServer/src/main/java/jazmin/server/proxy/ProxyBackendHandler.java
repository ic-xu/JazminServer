package jazmin.server.proxy;

import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import jazmin.log.Logger;
import jazmin.log.LoggerFactory;
/**
 *
 * @author yama
 *
 */
public class ProxyBackendHandler extends SimpleChannelInboundHandler {
	private static Logger logger=LoggerFactory.get(ProxyFrontendHandler.class);
	//
    private final Channel inboundChannel;
    //
    public ProxyBackendHandler(Channel inboundChannel) {
        this.inboundChannel = inboundChannel;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ctx.read();
        ctx.write(Unpooled.EMPTY_BUFFER);
    }

    @Override
    public void channelRead(final ChannelHandlerContext ctx, Object msg)throws Exception {
        inboundChannel.writeAndFlush(msg).addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) {
                if (future.isSuccess()) {
                    ctx.channel().read();
                } else {
                    future.channel().close();
                }
            }
        });
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        channelRead(channelHandlerContext,o);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        ProxyFrontendHandler.closeOnFlush(inboundChannel);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    	logger.catching(cause);
        ProxyFrontendHandler.closeOnFlush(ctx.channel());
    }
}
