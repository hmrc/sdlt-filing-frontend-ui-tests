#!/usr/bin/env bash

BROWSER=$1
ENVIRONMENT=$2
sbt clean compile -Dbrowser="${BROWSER:=chrome}" -Daccessibility.timeout=10000.millis -Denvironment="${ENVIRONMENT:=local}" -Dbrowser.usePreviousVersion=true -Dbrowser.option.headless=true "testOnly uk.gov.hmrc.ui.specs* -- -n uk.gov.hmrc.ui.tags.e2eJourney" testReport
