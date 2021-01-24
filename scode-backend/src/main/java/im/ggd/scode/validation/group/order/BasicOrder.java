package im.ggd.scode.validation.group.order;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

import im.ggd.scode.validation.group.EmptyGroup;
import im.ggd.scode.validation.group.ExistsGroup;
import im.ggd.scode.validation.group.LengthGroup;

@GroupSequence({ Default.class, EmptyGroup.class, LengthGroup.class, ExistsGroup.class })
public interface BasicOrder {

}
