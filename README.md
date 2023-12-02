<div class="markdown prose w-full break-words dark:prose-invert light" bis_skin_checked="1"><h1>MyFit Application</h1><h2>Overview</h2><p>MyFit is an application designed for managing workout programs, exercises, and user information. The foundation of the application relies on the Spring Boot framework, integrating Spring Data JPA, Thymeleaf for templating, and PostgreSQL as the primary database.</p><h2>Configuration</h2><h3><code>application.properties</code></h3><pre><div class="bg-black rounded-md" bis_skin_checked="1"><div class="flex items-center relative text-gray-200 bg-gray-800 dark:bg-token-surface-primary px-4 py-2 text-xs font-sans justify-between rounded-t-md" bis_skin_checked="1"><span>properties</span><button class="flex gap-1 items-center"><svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg" class="icon-sm"><path fill-rule="evenodd" clip-rule="evenodd" d="M12 4C10.8954 4 10 4.89543 10 6H14C14 4.89543 13.1046 4 12 4ZM8.53513 4C9.22675 2.8044 10.5194 2 12 2C13.4806 2 14.7733 2.8044 15.4649 4H17C18.6569 4 20 5.34315 20 7V19C20 20.6569 18.6569 22 17 22H7C5.34315 22 4 20.6569 4 19V7C4 5.34315 5.34315 4 7 4H8.53513ZM8 6H7C6.44772 6 6 6.44772 6 7V19C6 19.5523 6.44772 20 7 20H17C17.5523 20 18 19.5523 18 19V7C18 6.44772 17.5523 6 17 6H16C16 7.10457 15.1046 8 14 8H10C8.89543 8 8 7.10457 8 6Z" fill="currentColor"></path></svg>Copy code</button></div><div class="p-4 overflow-y-auto" bis_skin_checked="1"><code class="!whitespace-pre hljs language-properties"># Database Configuration
# Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/myfit
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.format_sql=true
spring.thymeleaf.prefix=classpath:/templates/
spring.thymeleaf.suffix=.html
spring.thymeleaf.cache=false

# Spring MVC Configuration
spring.mvc.hiddenmethod.filter.enabled=true

# Hibernate Logging Configuration
logging.level.org.hibernate.SQL=debug

# JWT (JSON Web Token) Configuration
jwt.secret=9A8E15241DDD69BC4EDAD3BEFF89B
jwt.lifeTime=PT1H

# Admin User Configuration
admin.user.name=AdminUser
admin.user.email=admin@example.com
admin.user.password=adminPassword
admin.user.roles=ADMIN

# Thymeleaf Security Authorization Configuration
spring.thymeleaf.security-authorization-enabled=true
</code></div></div></pre><h2>Project Structure</h2><p>The project adheres to the MVC (Model-View-Controller) architecture.</p><h2>Project Status</h2><p>The project is currently in progress, with the following milestones:</p><p><strong>Done:</strong></p><ul><li>Booted Spring application using MVC architecture.</li><li>Utilized Thymeleaf for displaying information.</li><li>Implemented DTO pattern.</li><li>Created Access Tokens, JWT request filter, and security configuration.</li></ul><p><strong>In Progress:</strong></p><ul><li>Addressing errors and exceptions.</li><li>Developing unit tests.</li><li>Containerizing with Docker and customizing deployment.</li></ul><p><strong>Future:</strong></p><ul><li>Adding API architecture for external communication.</li><li>Exploring Kotlin and developing an Android app.</li></ul><h2>Getting Started</h2><h3>Clone the Repository:</h3><ol><li>Open a terminal or command prompt.</li><li>Run the following command to clone the repository:<pre><div class="bg-black rounded-md" bis_skin_checked="1"><div class="flex items-center relative text-gray-200 bg-gray-800 dark:bg-token-surface-primary px-4 py-2 text-xs font-sans justify-between rounded-t-md" bis_skin_checked="1"><span>bash</span><button class="flex gap-1 items-center"><svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg" class="icon-sm"><path fill-rule="evenodd" clip-rule="evenodd" d="M12 4C10.8954 4 10 4.89543 10 6H14C14 4.89543 13.1046 4 12 4ZM8.53513 4C9.22675 2.8044 10.5194 2 12 2C13.4806 2 14.7733 2.8044 15.4649 4H17C18.6569 4 20 5.34315 20 7V19C20 20.6569 18.6569 22 17 22H7C5.34315 22 4 20.6569 4 19V7C4 5.34315 5.34315 4 7 4H8.53513ZM8 6H7C6.44772 6 6 6.44772 6 7V19C6 19.5523 6.44772 20 7 20H17C17.5523 20 18 19.5523 18 19V7C18 6.44772 17.5523 6 17 6H16C16 7.10457 15.1046 8 14 8H10C8.89543 8 8 7.10457 8 6Z" fill="currentColor"></path></svg>Copy code</button></div><div class="p-4 overflow-y-auto" bis_skin_checked="1"><code class="!whitespace-pre hljs language-bash">git <span class="hljs-built_in">clone</span> &lt;repository-url&gt;
</code></div></div></pre></li><li>Replace <code>&lt;repository-url&gt;</code> with the actual URL of the MyFit repository.</li></ol><h3>Configure PostgreSQL Database:</h3><ol><li>Ensure you have Java and Maven installed on your machine.</li><li>Navigate to the root directory of the cloned repository in the terminal.</li><li>Run the following command to build the application:<pre><div class="bg-black rounded-md" bis_skin_checked="1"><div class="flex items-center relative text-gray-200 bg-gray-800 dark:bg-token-surface-primary px-4 py-2 text-xs font-sans justify-between rounded-t-md" bis_skin_checked="1"><span>bash</span><button class="flex gap-1 items-center"><svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg" class="icon-sm"><path fill-rule="evenodd" clip-rule="evenodd" d="M12 4C10.8954 4 10 4.89543 10 6H14C14 4.89543 13.1046 4 12 4ZM8.53513 4C9.22675 2.8044 10.5194 2 12 2C13.4806 2 14.7733 2.8044 15.4649 4H17C18.6569 4 20 5.34315 20 7V19C20 20.6569 18.6569 22 17 22H7C5.34315 22 4 20.6569 4 19V7C4 5.34315 5.34315 4 7 4H8.53513ZM8 6H7C6.44772 6 6 6.44772 6 7V19C6 19.5523 6.44772 20 7 20H17C17.5523 20 18 19.5523 18 19V7C18 6.44772 17.5523 6 17 6H16C16 7.10457 15.1046 8 14 8H10C8.89543 8 8 7.10457 8 6Z" fill="currentColor"></path></svg>Copy code</button></div><div class="p-4 overflow-y-auto" bis_skin_checked="1"><code class="!whitespace-pre hljs language-bash">mvn clean install
</code></div></div></pre></li><li>Once the build is successful, run the following command to start the Spring Boot application:<pre><div class="bg-black rounded-md" bis_skin_checked="1"><div class="flex items-center relative text-gray-200 bg-gray-800 dark:bg-token-surface-primary px-4 py-2 text-xs font-sans justify-between rounded-t-md" bis_skin_checked="1"><span>bash</span><button class="flex gap-1 items-center"><svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg" class="icon-sm"><path fill-rule="evenodd" clip-rule="evenodd" d="M12 4C10.8954 4 10 4.89543 10 6H14C14 4.89543 13.1046 4 12 4ZM8.53513 4C9.22675 2.8044 10.5194 2 12 2C13.4806 2 14.7733 2.8044 15.4649 4H17C18.6569 4 20 5.34315 20 7V19C20 20.6569 18.6569 22 17 22H7C5.34315 22 4 20.6569 4 19V7C4 5.34315 5.34315 4 7 4H8.53513ZM8 6H7C6.44772 6 6 6.44772 6 7V19C6 19.5523 6.44772 20 7 20H17C17.5523 20 18 19.5523 18 19V7C18 6.44772 17.5523 6 17 6H16C16 7.10457 15.1046 8 14 8H10C8.89543 8 8 7.10457 8 6Z" fill="currentColor"></path></svg>Copy code</button></div><div class="p-4 overflow-y-auto" bis_skin_checked="1"><code class="!whitespace-pre hljs language-bash">java -jar target/myfit-&lt;version&gt;.jar
</code></div></div></pre></li><li>Replace <code>&lt;version&gt;</code> with the actual version of the application.</li></ol><h3>Access the Application:</h3><ol><li>Open a web browser.</li><li>Navigate to <a href="http://localhost:8080" target="_new">http://localhost:8080</a> (or the configured port) to access the MyFit application.</li></ol></div>