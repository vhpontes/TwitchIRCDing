package ircding;

import static ircding.IRCDing.buttonPlaySE;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.hooks.events.NoticeEvent;


public class TwitchChatListener extends ListenerAdapter {
    
    IRCDing appPanel;
    int i = 0;
    
    public TwitchChatListener() {
       
    }
    public TwitchChatListener(IRCDing appPanel) {
       
    }
    
    @Override
    public void onNotice(NoticeEvent event)throws InterruptedException {
        int mapNum = 0;
        int userHashCode = 0;
        
        String twitchNotice = event.getNotice();
        userHashCode = event.getUser().hashCode();
        
        DateFormat f = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    }
    
    @Override
    public void onMessage(MessageEvent event) throws InterruptedException {
        
        int userHashCode = 0;
        String twitchMessage = event.getMessage();
        userHashCode = event.getUser().hashCode();
        
        DateFormat datehour = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        
        if(appPanel.checkBoxDingOn.isSelected()) {
            if(!event.getUser().getNick().equals("nightbot")) {
                System.out.println(datehour.format(event.getTimestamp()) + ": " + twitchMessage);
                appPanel.buttonPlaySE.doClick();
            }
        }
    }
}