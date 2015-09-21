server {
        listen ${port} <#if defaultServer>default_server</#if>;
<#if ipv6>
        listen [::]:${port} <#if defaultServer>default_server</#if>;
</#if>
        root ${root};
        index index.html index.htm;

        server_name ${serverName};

        location / {
            <#if proxyPass>
                proxy_pass ${proxy};
                access_log off;
                proxy_set_header X-Real-IP $remote_addr;
                proxy_set_header Host $host;
                proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
            <#else>
                try_files $uri $uri/ =404;
            </#if>
        }
}