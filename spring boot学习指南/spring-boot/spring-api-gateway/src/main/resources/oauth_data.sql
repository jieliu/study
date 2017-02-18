INSERT INTO oauth_client_details
  (client_id, resource_ids, client_secret, scopesof, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove)
VALUES
  ('read-only-client', 'todo-services', null, 'read', 'implicit', 'http://114.215.151.54', NULL, 7200, 0, NULL, 'false');

INSERT INTO oauth_client_details
  (client_id, resource_ids, client_secret, scopesof, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove)
VALUES
  ('curl-client', 'todo-services', 'client-secret', 'read,write', 'client_credentials', '', 'ROLE_ADMIN', 7200, 0, NULL, 'false');