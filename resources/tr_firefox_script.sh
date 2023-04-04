#!/usr/bin/bash

###################################################################################################
#     This is extracting the values for each test suite description.                              #
#     It uses awk to print the value between two quotes.                                          #
#     The columns printed is based on the following example string:                               #
#     <testng-results ignored="0" total="23" passed="22" failed="1" skipped="0">                  #
###################################################################################################

TEST_RESULTS_LOCATION="${1:-/home/runner/work/ebanking/ebanking/target/surefire-reports}"
# shellcheck disable=SC2002
TEST_RESULTS_STRING=$(cat "${TEST_RESULTS_LOCATION}/testng-results.xml" | grep "<testng-results")

cat <<EOF | curl --data-binary @- "${PUSHGATEWAY_URL}"/metrics/job/github_actions
github_actions_ignored_tests_firefox{action_id="${GITHUB_RUN_NUMBER}",commit="${GITHUB_SHA}",actor="${GITHUB_ACTOR}",branch="${GITHUB_REF}",os="${RUNNER_OS}",browser="${GITHUB_JOB}"} $(echo "${TEST_RESULTS_STRING}" | awk -F'"' '/ignored/ {print $2}')
github_actions_total_tests_firefox{action_id="${GITHUB_RUN_NUMBER}",commit="${GITHUB_SHA}",actor="${GITHUB_ACTOR}",branch="${GITHUB_REF}",os="${RUNNER_OS}",browser="${GITHUB_JOB}"} $(echo "${TEST_RESULTS_STRING}" | awk -F'"' '/total/ {print $2}')
github_actions_passed_tests_firefox{action_id="${GITHUB_RUN_NUMBER}",commit="${GITHUB_SHA}",actor="${GITHUB_ACTOR}",branch="${GITHUB_REF}",os="${RUNNER_OS}",browser="${GITHUB_JOB}"} $(echo "${TEST_RESULTS_STRING}" | awk -F'"' '/passed/ {print $2}')
github_actions_failed_tests_firefox{action_id="${GITHUB_RUN_NUMBER}",commit="${GITHUB_SHA}",actor="${GITHUB_ACTOR}",branch="${GITHUB_REF}",os="${RUNNER_OS}",browser="${GITHUB_JOB}"} $(echo "${TEST_RESULTS_STRING}" | awk -F'"' '/failed/ {print $2}')
github_actions_skipped_tests_firefox{action_id="${GITHUB_RUN_NUMBER}",commit="${GITHUB_SHA}",actor="${GITHUB_ACTOR}",branch="${GITHUB_REF}",os="${RUNNER_OS}",browser="${GITHUB_JOB}"} $(echo "${TEST_RESULTS_STRING}" | awk -F'"' '/skipped/ {print $2}')
EOF

# shellcheck disable=SC2129
echo "gha.maventest.ignored=$(echo "${TEST_RESULTS_STRING}" | awk -F'"' '/ignored/ {print $2}')" >> "$BUILDEVENT_FILE"
echo "gha.maventest.total=$(echo "${TEST_RESULTS_STRING}" | awk -F'"' '/total/ {print $2}')" >> "$BUILDEVENT_FILE"
echo "gha.maventest.passed=$(echo "${TEST_RESULTS_STRING}" | awk -F'"' '/passed/ {print $2}')" >> "$BUILDEVENT_FILE"
echo "gha.maventest.failed=$(echo "${TEST_RESULTS_STRING}" | awk -F'"' '/failed/ {print $2}')" >> "$BUILDEVENT_FILE"
echo "gha.maventest.skipped=$(echo "${TEST_RESULTS_STRING}" | awk -F'"' '/skipped/ {print $2}')" >> "$BUILDEVENT_FILE"
