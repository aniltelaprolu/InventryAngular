package com.inventory.web.security;

//@Component
public class CustomUserNamePasswordAuthenticationFilter{/* extends UsernamePasswordAuthenticationFilter {

	public CustomUserNamePasswordAuthenticationFilter() {
		super();
	}
	
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {
		if (!request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException(
					"Authentication method not supported: " + request.getMethod());
		}

		String username = obtainUsername(request);
		String password = obtainPassword(request);

		if (username == null) {
			username = "";
		}

		if (password == null) {
			password = "";
		}

		username = username.trim();

		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
				username, password);

		// Allow subclasses to set the "details" property
		setDetails(request, authRequest);

		return this.getAuthenticationManager().authenticate(authRequest);
	}

	protected String obtainUsername(HttpServletRequest request) {
		return request.getParameter("email");
	}
	
	protected String obtainPassword(HttpServletRequest request) {
		return request.getParameter("password");
	}*/
}
