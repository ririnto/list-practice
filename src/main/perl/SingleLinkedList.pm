package SingleLinkedList;
use strict;
use warnings FATAL => 'all';

use SingleLinkedList::Node;

sub new {
    my $class = shift;

    my $head = SingleLinkedList::Node->new();

    bless {
            head => $head,
            size => 0
    }, $class;
}

sub getSize {
    my $self = shift;

    return $self->{size};
}

sub append {
    my $self = shift;
    my $value = shift;

    my $node;
    if ($self->{size} == 0) {
        $node = $self->{head};
    }
    else {
        $node = $self->getNodeAt($self->{size} - 1)
    }

    $node->{next} = SingleLinkedList::Node->new($value);

    $self->{size}++;
}

sub appendLeft {
    my $self = shift;
    my $value = shift;

    my $node = SingleLinkedList::Node->new($value);
    $self->{head}->{next} = $node;

    $self->{size}++;
}

sub insert {
    my $self = shift;
    my $index = shift;
    my $value = shift;

    if ($index == 0) {
        $self->appendLeft($value);
    }
    elsif ($index == $self->{size} - 1) {
        $self->append($value)
    }
    else {
        my $prevNode = $self->getNodeAt($index - 1);
        my $node = SingleLinkedList::Node->new($value);

        $node->{next} = $prevNode->{next};
        $prevNode->{next} = $node;

        $self->{size}++;
    }
}

sub get {
    my $self = shift;
    my $index = shift;

    $self->getNodeAt($index)->{value};
}

sub removeAt {
    my $self = shift;
    my $index = shift;

    $self->checkIndex($index);

    my $prevNode;
    if ($index == 0) {
        $prevNode = $self->{head};
    }
    else {
        $prevNode = $self->getNodeAt($index - 1)
    }

    my $node = $prevNode->{next};
    $prevNode->{next} = $node;

    $self->{size}--;

    $node->{value};
}

sub getNodeAt {
    my $self = shift;
    my $index = shift;

    $self->checkIndex($index);

    my $node = $self->{head};
    for (0 .. $index) {
        $node = $node->{next};
    }

    $node;
}

sub checkIndex {
    my $self = shift;
    my $index = shift;

    if ($index < 0 || $self->{size} <= $index) {
        die "IndexError"
    }
}

1;
