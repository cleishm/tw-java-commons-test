package com.thoughtworks.test;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;

public final class JettyServer {
    private static final String USAGE = "Usage: jettyserver webapp-path port [context]\n";
    
	private final Server server;
	private final String webbapp;
	private final String context;

	public static void main(final String[] args) throws Exception {
        if (args.length < 2) {
        	System.err.print(USAGE);
        	System.exit(1);
        }

        final String webapp = args[0];
        final int port = Integer.parseInt(args[1]);
        final String context = (args.length < 3) ? "/" : args[2];

		final JettyServer jettyServer = new JettyServer(webapp, port, context);
		jettyServer.start();
		jettyServer.join();
	}

	public JettyServer(final String webbapp, final int port, final String context) {
		this.webbapp = webbapp;
		this.context = context;
		server = new Server(port);
	}

	public void start() throws Exception {
		final WebAppContext webapp = new WebAppContext(webbapp, context);
		server.addHandler(webapp);
		server.start();
	}

	public void stop() throws Exception {
		server.stop();
	}

	public void join() throws Exception {
		server.join();
	}
}
