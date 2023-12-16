package pl.backyard.backyardleaguebackend.core.functionality.common.validator.user.sequence;

import jakarta.validation.GroupSequence;
import jakarta.validation.groups.Default;

@GroupSequence({First.class, Default.class})
public interface FirstAndDefaultSequence {
}
