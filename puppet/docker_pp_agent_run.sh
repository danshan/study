#!/bin/sh

name=$1
hostname=$2

docker run -i -t -P --name $name -h $hostname danshan/puppet /bin/bash
