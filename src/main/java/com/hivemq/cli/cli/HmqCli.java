package com.hivemq.cli.cli;

import com.hivemq.cli.commands.AbstractCommand;
import com.hivemq.cli.commands.Connect;
import com.hivemq.cli.commands.Subscribe;
import com.hivemq.cli.commands.shell.Shell;
import com.hivemq.cli.impl.ConnectionImpl;
import com.hivemq.cli.impl.SubscriptionImpl;
import picocli.CommandLine;

public class HmqCli {

    public static boolean executeCommand(AbstractCommand subCommand) {
        try {

            if (subCommand instanceof Subscribe) {
                Subscribe subscribe = (Subscribe) subCommand;
                SubscriptionImpl subscription = new SubscriptionImpl(subscribe);
                subscription.run();
            } else if (subCommand instanceof Connect) {
                Connect connect = (Connect) subCommand;
                ConnectionImpl connection = new ConnectionImpl(connect);
                connection.run();
            } else if (subCommand instanceof Shell) {
                ((Shell) subCommand).run();
            }

        } catch (CommandLine.ParameterException ex) {
            System.err.println(ex.getMessage());
            ex.getCommandLine().usage(System.err);
        } catch (Exception others) {
            // suppress classname
            System.err.println(others.getCause().getMessage());
        }
        return false;
    }


}
