package ru.kata.spring.boot_security.demo.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Data
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Role(Long valueOf, String user) {
        id = valueOf;
        name = user;
    }
    public Role(){

    }

    @Override
    public String getAuthority() {
        return getName();
    }
}


/*<td><ul>
<li>
<input id="features1" name="features" type="checkbox" value="SEEDSTARTER_SPECIFIC_SUBSTRATE" />
<input name="_features" type="hidden" value="on" />
<label for="features1">Admin</label>
</li></ul></td>
<td><ul>
<li>
<input id="features2" name="features" type="checkbox" value="FERTILIZER" />
<input name="_features" type="hidden" value="on" />
<label for="features2">User</label>
</li>
</ul></td> */