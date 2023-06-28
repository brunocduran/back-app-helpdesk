package br.com.project.helpdesk.resources.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FieldMessage  implements Serializable {
    private static final long serialVersionUID = 1L;

    private String fieldName;
    private String message;
}
