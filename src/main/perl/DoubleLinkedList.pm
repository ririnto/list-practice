package DoubleLinkedList;
use strict;
use warnings FATAL => 'all';

use DoubleLinkedList::Node;

sub new {
    my $class = shift;

    my $head = DoubleLinkedList::Node->new();
    my $tail = DoubleLinkedList::Node->new();

    $head->{next} = $tail;
    $tail->{prev} = $head;

    bless {
            head => $head,
            tail => $tail,
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

    my $newNode = DoubleLinkedList::Node->new($value);
    $newNode->{prev} = $self->{tail}->{prev};
    $newNode->{next} = $self->{tail};
    $self->{tail}->{prev}->{next} = $newNode;
    $self->{tail}->{prev} = $newNode;

    $self->{size}++;
}

sub appendLeft {
    my $self = shift;
    my $value = shift;

    my $newNode = DoubleLinkedList::Node->new($value);
    $newNode->{prev} = $self->{head};
    $newNode->{next} = $self->{head}->{next};
    $self->{head}->{next}->{prev} = $newNode;
    $self->{head}->{next} = $newNode;

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
        my $nextNode = $self->getNodeAt($index);
        my $node = DoubleLinkedList::Node->new($value);

        $nextNode->{prev}->{next} = $node;
        $node->{prev} = $nextNode->{prev};
        $node->{next} = $nextNode;
        $nextNode->{prev} = $node;

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

    my $node = $self->getNodeAt($index);
    $node->{prev}->{next} = $node->{next};
    $node->{next}->{prev} = $node->{prev};

    $self->{size}--;

    $node->{value};
}

sub getNodeAt {
    my $self = shift;
    my $index = shift;

    $self->checkIndex($index);

    my $node;
    if ($index < $self->{size} / 2) {
        $node = $self->{head};
        for (0 .. $index) {
            $node = $node->{next};
        }
    }
    else {
        $node = $self->{tail};
        for (0 .. $self->{size} - $index - 1) {
            $node = $node->{prev};
        }
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
