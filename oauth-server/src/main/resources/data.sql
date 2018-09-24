INSERT INTO oauth_client_details
	(client_id, client_secret, scope, authorized_grant_types,
	web_server_redirect_uri, authorities, access_token_validity,
	refresh_token_validity, additional_information, autoapprove)
VALUES
	('lin', 'secret', 'read,write',
	'password,authorization_code,implicit,refresh_token', 'http://microservice', null, 36000, 36000, null, true);
