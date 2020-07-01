package com.v1.CentralDeErros.config;

import com.v1.CentralDeErros.models.Permission;
import com.v1.CentralDeErros.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class PermissionFilter extends OncePerRequestFilter {

    // Padrão utilizado pela Regexp que definirá qual URI devemos aplicar o filtro.
    static final String URI_PATTERN = ".*/applications/([0-9]+).*";

    private final Pattern uriPatternObj = Pattern.compile(URI_PATTERN);

    private final UserService userService;

    @Autowired
    public PermissionFilter(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        /* Só queremos aplicar o filtro à URIs que respeitem a Regexp definida por URI_PATTERN. Logo, se a URI não
         * respeita a URI_PATTERN, {path.matches(URI_PATTERN)} retorna FALSE, o que indica que não devemos filtrá-la,
         * isto é, shouldNotFilter deve retornar TRUE - logo, colocamos o negador {!} para que o resultado respeite
         * a lógica esperada por shouldNotFilter. */
        return !path.matches(URI_PATTERN);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String requestURI = request.getRequestURI();

        /* Aqui, capturamos o grupo de número 1 da Regexep definida por URI_PATTERN. Isto é, capturamos o id da
         * aplicação. Temos a garantia que requestURI obedece a Regexp - uma vez que ela passou pelo método
         * {shouldNotFilter} acima. */
        Matcher uriMatcher = uriPatternObj.matcher(requestURI);

        /* Inicializamos o applicationId com -1 dado que este não é um id válido de aplicação e nos blinda de possíveis
         * erros. */
        int applicationId = -1;

        if (uriMatcher.find()) {
            applicationId = Integer.parseInt(uriMatcher.group(1));
        } else {
            /* Tenicamente, como temos a garantia que a URI da aplicação respeita a Regexp de URI_PATTERN, não
             * há como essa exceção ser lançada no código como ele está agora. Logicamente, caso ele seja modificado,
             * não teremos mais essa garantia, então é importante mesmo assim levarmos em conta essa exceção. */
            throw new BadCredentialsException("Erro na identificação da aplicação.");
        }

        String currentUsername = userService.getCurrentUserName();

        List<Permission> userPermissions = userService.permissions(currentUsername);

        Integer finalApplicationId = applicationId;

        Optional<Integer> permittedApplicationId = userPermissions.stream()
                .map(permission -> permission.getId().getApplicationInstance().getId())
                .filter(id -> id.equals(finalApplicationId))
                .findFirst();

        if (!permittedApplicationId.isPresent()) {
            throw new AccessDeniedException("Usuário não tem permissão para essa aplicação");
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
