package SingleLinkedList::Node;
use strict;
use warnings FATAL => 'all';

sub new {
    my $class = shift;

    bless {
            value => shift || undef,
            next  => undef
    }, $class;
}

1;
